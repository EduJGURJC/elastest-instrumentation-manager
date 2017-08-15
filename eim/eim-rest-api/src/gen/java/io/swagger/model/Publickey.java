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
 * OpenAPI spec version: 1.0.0
 * 
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 * 
 * Developed in the context of ElasTest EU project http://elastest.io 
 */

package io.swagger.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity defines the publickey of EIM.
 */
@ApiModel(description = "This entity defines the publickey of EIM.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-08-15T11:10:32.030+02:00")
public class Publickey   {
  @JsonProperty("publickey")
  private String publickey = null;

  public Publickey publickey(String publickey) {
    this.publickey = publickey;
    return this;
  }

  /**
   * Get publickey
   * @return publickey
   **/
  @JsonProperty("publickey")
  @ApiModelProperty(example = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCp9ujzo4XgfcFFHZqqHvf25PP/TSAP89pxSx9sqsNsY7EPOMTpQVpYRuooaJQk7pwr8SzHJkWA2KzNcfs1hneIsfWPqhDmeDliwm3cFIrPQ3juFaoD03eRzS36F+qKyN8og5PxSCPSBtDdvdn0gvTA9j1IXQJ4oeUIxr3s8dIJiYpKY3wQeJTVCRQNGHZWrnjgSZ0YCH0wiEpUAT4mfUWmpjSyVmH8q2l2REuJbgVy5rQ7sMqaf/BvbAiKgqLYDOrcGQNjGj6in1Fuwj9vPvhyvRaNrkfj2NuA5RFN+2wPbYyClxEurFJWsD/GEHNKnd/0X88ubhZ89sIEiUYL8KB root@0b83a3aa4d3a", required = true, value = "")
  @NotNull
  public String getPublickey() {
    return publickey;
  }

  public void setPublickey(String publickey) {
    this.publickey = publickey;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Publickey publickey = (Publickey) o;
    return Objects.equals(this.publickey, publickey.publickey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(publickey);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Publickey {\n");
    
    sb.append("    publickey: ").append(toIndentedString(publickey)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

