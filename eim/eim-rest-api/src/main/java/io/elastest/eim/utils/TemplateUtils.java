/** 
 * Copyright (c) 2017 Atos
 * This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0
 * which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *    @author David Rojo Antona (Atos)
 *    
 * Developed in the context of ElasTest EU project http://elastest.io 
 */

package io.elastest.eim.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import io.elastest.eim.config.Dictionary;
import io.elastest.eim.config.Properties;
import io.swagger.model.AgentFull;

public class TemplateUtils {

	private static Logger logger = Logger.getLogger(TemplateUtils.class);
	
	public static String generatePlaybook(String type, String executionDate, AgentFull agent, String user) {
		if (type.equalsIgnoreCase("ssh")) {
			
			String playbookTemplatePath = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_SSH_EXECUTIONPATH) + 
					Properties.getValue(Dictionary.PROPERTY_TEMPLATES_SSH_PLAYBOOK);
			String playbookToExecutePath = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_SSH_EXECUTIONPATH) + 
					Properties.getValue(Dictionary.PROPERTY_TEMPLATES_SSH_EXECUTION_PLAYBOOK_PREFIX) + agent.getAgentId() + 
					"-" + executionDate + ".yml";
			String jokerTemplates = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_PLAYBOOK_JOKER);
			String jokerUser = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_USER_JOKER);
			
			try {
				//Generate the execution playbook
				FileTextUtils.copyFile(playbookTemplatePath, playbookToExecutePath);
				logger.info("Generated successfully the SSH playbook for agent" + agent.getAgentId() + ": " + playbookToExecutePath);
				//Fill the playbook with the agentId of the agent
				FileTextUtils.replaceTextInFile(playbookToExecutePath, jokerTemplates, agent.getAgentId());
				//Fill the playbook with the user of the new host
				FileTextUtils.replaceTextInFile(playbookToExecutePath, jokerUser, user);
				logger.info("Modified successfully the generated SSH playbook for agent " + agent.getAgentId() + ". Ready to execute!");
				return playbookToExecutePath;				
			} catch (IOException e) {
				logger.error("ERROR while triying to generate SSH playbook for agent " + agent.getAgentId() + " with execution date: " + executionDate);
				e.printStackTrace();
				return "";
			}			
		}
		else if (type.equalsIgnoreCase("beats")) {			

			String playbookTemplatePath = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_BEATS_PLAYBOOKPATH) + 
					Properties.getValue(Dictionary.PROPERTY_TEMPLATES_BEATS_PLAYBOOK);
			String playbookToExecutePath = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_BEATS_PLAYBOOKPATH) + 
					Properties.getValue(Dictionary.PROPERTY_TEMPLATES_BEATS_EXECUTION_PLAYBOOK_PREFIX) + agent.getAgentId() + 
					"-" + executionDate + ".yml";
			String jokerTemplates = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_PLAYBOOK_JOKER);
			String jokerLogstashIp = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_BEATS_JOKER_LOGSTASH_IP);
			String jokerLogstashPort = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_BEATS_JOKER_LOGSTASH_PORT);
			
			try {
				//Generate the execution playbook
				FileTextUtils.copyFile(playbookTemplatePath, playbookToExecutePath);
				logger.info("Generated successfully the SSH playbook for agent" + agent.getAgentId() + ": " + playbookToExecutePath);
				//Fill the playbook with the agentId of the agent
				FileTextUtils.replaceTextInFile(playbookToExecutePath, jokerTemplates, agent.getAgentId());
				//Fill the playbook with the logstash IP of the agent
				FileTextUtils.replaceTextInFile(playbookToExecutePath, jokerLogstashIp, agent.getLogstashIp());
				//Fill the playbook with the logstash port of the agent
				FileTextUtils.replaceTextInFile(playbookToExecutePath, jokerLogstashPort, agent.getLogstashPort());
				logger.info("Modified successfully the generated SSH playbook for agent " + agent.getAgentId() + ". Ready to execute!");
				return playbookToExecutePath;				
			} catch (IOException e) {
				logger.error("ERROR while triying to generate SSH playbook for agent " + agent.getAgentId() + " with execution date: " + executionDate);
				e.printStackTrace();
				return "";
			}		
			
		}
		return "";
		 
	}
	
	
	public static String generateScript(String type, String executionDate, AgentFull agent, String playbookPath, String ansibleCfgFilePath) {
		if (type.equalsIgnoreCase("ssh")) {
			
			String scriptTemplatePath = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_SSH_EXECUTIONPATH) + 
					Properties.getValue(Dictionary.PROPERTY_TEMPLATES_SSH_LAUNCHER);
			String scriptToExecutePath = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_SSH_EXECUTIONPATH) + 
					Properties.getValue(Dictionary.PROPERTY_TEMPLATES_SSH_EXECUTION_LAUNCHER_PREFIX) + agent.getAgentId() +"-" + executionDate + ".sh";
			String jokerPlaybookTemplates = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_SCRIPT_JOKER_PLAYBOOK);
			String jokerCfgFileTemplates = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_SCRIPT_JOKER_CONFIG);
			
			try {
				//Generate the execution playbook
				FileTextUtils.copyFile(scriptTemplatePath, scriptToExecutePath);
				logger.info("Generated successfully the SSH script for agent" + agent.getAgentId() + ": " + scriptToExecutePath);
				//Fill the playbook with the agentId of the agent
				FileTextUtils.replaceTextInFile(scriptToExecutePath, jokerPlaybookTemplates, playbookPath);
				//Fill the playbook with the specific ansible config file of the agent
				FileTextUtils.replaceTextInFile(scriptToExecutePath, jokerCfgFileTemplates, ansibleCfgFilePath);
				//set the file as executable
				FileTextUtils.setAsExecutable(scriptToExecutePath);
				logger.info("Modified successfully the generated SSH script for agent " + agent.getAgentId() + ". Ready to execute!");
				return scriptToExecutePath;
				
			} catch (IOException e) {
				logger.error("ERROR while triying to generate SSH script for agent " + agent.getAgentId() + " with execution date: " + executionDate);
				e.printStackTrace();
				return "";
			}				
			
		}
		else if (type.equalsIgnoreCase("beats")) {
			
			
			String scriptTemplatePath = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_BEATS_EXECUTIONPATH) + 
					Properties.getValue(Dictionary.PROPERTY_TEMPLATES_BEATS_LAUNCHER);
			String scriptToExecutePath = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_BEATS_EXECUTIONPATH) + 
					Properties.getValue(Dictionary.PROPERTY_TEMPLATES_BEATS_EXECUTION_LAUNCHER_PREFIX) + agent.getAgentId() +"-" + executionDate + ".sh";
			String jokerTemplates = Properties.getValue(Dictionary.PROPERTY_TEMPLATES_SCRIPT_JOKER_PLAYBOOK);
			
			try {
				//Generate the execution playbook
				FileTextUtils.copyFile(scriptTemplatePath, scriptToExecutePath);
				logger.info("Generated successfully the Beats script for agent" + agent.getAgentId() + ": " + scriptToExecutePath);
				
				FileTextUtils.replaceTextInFile(scriptToExecutePath, jokerTemplates, playbookPath);
				//set the file as executable
				FileTextUtils.setAsExecutable(scriptToExecutePath);
				logger.info("Modified successfully the generated Beats script for agent " + agent.getAgentId() + ". Ready to execute!");
				return scriptToExecutePath;
				
			} catch (IOException e) {
				logger.error("ERROR while triying to generate Beats script for agent " + agent.getAgentId() + " with execution date: " + executionDate);
				e.printStackTrace();
				return "";
			}	
			
		}
		return "";
			 
	}


	public static int executeScript(String type, String scriptPath, String executionDate, AgentFull agent) {
		// TODO Auto-generated method stub
		int resultCode = -1;
		String logFilePath = "";
		if (type.equalsIgnoreCase("ssh")) { 
			logFilePath = Properties.getValue(Dictionary.PROPERTY_EXECUTION_LOGS_PATH) + 
					Properties.getValue(Dictionary.PROPERTY_EXECUTION_LOGS_SSH_PREFIX) + 
					agent.getAgentId() +"_" + executionDate + ".log";
		}
		else if (type.equalsIgnoreCase("beats")) {
			logFilePath = Properties.getValue(Dictionary.PROPERTY_EXECUTION_LOGS_PATH) + 
					Properties.getValue(Dictionary.PROPERTY_EXECUTION_LOGS_BEATS_PREFIX) + 
					agent.getAgentId() +"_" + executionDate + ".log";
		}
		else {
			logger.error("ERROR: not recognized type (" + type + ") to execute the script " + scriptPath);
			return resultCode;
		}

		String s;
    	Process p;
    	try {
            p = Runtime.getRuntime().exec(scriptPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
      
            File logFile = new File(logFilePath);
            PrintWriter writer = new PrintWriter(logFile);
            
            while ((s = br.readLine()) != null){
            	logger.info(s);
            	writer.println(s);
            }                
            p.waitFor();
            resultCode = p.exitValue();
            logger.info("exit: " + resultCode);
            writer.println("Result code: " + resultCode);
            writer.close();
            p.destroy();
            return resultCode;
        } catch (Exception e) {
        	logger.error("ERROR: " + e.getLocalizedMessage());
        	logger.error(e.getStackTrace());
        	return resultCode;
        }		
	}
}
