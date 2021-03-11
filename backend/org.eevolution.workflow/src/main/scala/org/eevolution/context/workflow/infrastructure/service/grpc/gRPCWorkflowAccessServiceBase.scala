/**
 * Copyright (C) 2003-2021, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.eevolution.context.workflow.infrastructure.service.grpc

import io.grpc.Status
import org.eevolution._
import org.eevolution.context.workflow.domain.workflowAccessRepositoryApi.WorkflowAccessRepository
import org.eevolution.context.workflow.domain.workflowAccessServiceApi
import org.eevolution.context.workflow.domain.workflowAccessServiceApi.WorkflowAccessService
import org.eevolution.context.workflow.infrastructure.service.grpc.workflow_access._
import org.eevolution.context.workflow.infrastructure.service.grpc.workflow_access.ZioWorkflowAccess.RWorkflowAccessService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WorkflowAccess Service Base Trait with the implementation for gRPC WorkflowAccess Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WorkflowAccess Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWorkflowAccessServiceBase {

  class Service[R] extends RWorkflowAccessService[WorkflowAccessService with WorkflowAccessRepository] {

    
    override def getWorkflowAccessAll(request: WorkflowAccessAllRequest): ZStream[
      WorkflowAccessService with WorkflowAccessRepository with Any,
      Status,
      WorkflowAccessStreamResponse
    ] =
      ZStream
        .fromEffect(
          workflowAccessServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              workflowAccessList =>
                WorkflowAccessStreamResponse.of(
                  workflowAccessList.map(workflowAccess => ~>(workflowAccess))
                )
            )
        )
    
    override def getWorkflowAccessAllByClientId(
        request: WorkflowAccessAllByClientIdRequest
    ): ZStream[
      WorkflowAccessService with WorkflowAccessRepository with Any,
      Status,
      WorkflowAccessStreamResponse
    ] =
      ZStream
        .fromEffect(
          workflowAccessServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              workflowAccessList =>
                WorkflowAccessStreamResponse.of(
                  workflowAccessList.map(workflowAccess => ~>(workflowAccess))
                )
            )
        )
    

override def save(request: WorkflowAccessSaveRequest): ZIO[
      WorkflowAccessService with WorkflowAccessRepository with Any,
      Status,
      WorkflowAccessResponse
    ] = {
      val workflowAccess = request.getWorkflowAccess
      workflowAccessServiceApi
        .save(as(workflowAccess))
        .bimap(
          _ => Status.NOT_FOUND,
          workflowAccess => WorkflowAccessResponse(~>?(workflowAccess))
        )
    }
    
    private def ~>?(
        workflowAccess: context.workflow.domain.model.WorkflowAccess
    ): Option[WorkflowAccess] = {
      Option(~>(workflowAccess))
    }

    private def ~>(
        workflowAccess: context.workflow.domain.model.WorkflowAccess
    ): WorkflowAccess = 
      WorkflowAccess(
        workflowAccess.workflowId,
        workflowAccess.clientId,
        workflowAccess.orgId,
        workflowAccess.isActive,
        workflowAccess.created,
        workflowAccess.createdBy,
        workflowAccess.updated,
        workflowAccess.updatedBy,
        workflowAccess.isReadWrite,
        workflowAccess.roleId,
        workflowAccess.uuid)


   private def as(workflowAccess: context.workflow.infrastructure.service.grpc.workflow_access.WorkflowAccess) = 
      context.workflow.domain.model.WorkflowAccess(
        workflowAccess.workflowId,
        workflowAccess.clientId,
        workflowAccess.orgId,
        workflowAccess.isActive,
        workflowAccess.created,
        workflowAccess.createdBy,
        workflowAccess.updated,
        workflowAccess.updatedBy,
        workflowAccess.isReadWrite,
        workflowAccess.roleId,
        workflowAccess.uuid)

 }
}