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

package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Events1;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-06-23T09:46:23.390Z")
public abstract class AgentApiService {
    public abstract Response deleteAgent(String agentId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getAgent(String agentId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response postAction(String agentId,String actionId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response postAgent(Events1 events,SecurityContext securityContext) throws NotFoundException;
    public abstract Response putAgent(String agentId,SecurityContext securityContext) throws NotFoundException;
//	public abstract Response getPublickey(Events1 events, SecurityContext securityContext) throws NotFoundException;
}
