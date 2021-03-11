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
import org.eevolution.context.workflow.domain.workflowTrlRepositoryApi.WorkflowTrlRepository
import org.eevolution.context.workflow.domain.workflowTrlServiceApi
import org.eevolution.context.workflow.domain.workflowTrlServiceApi.WorkflowTrlService
import org.eevolution.context.workflow.infrastructure.service.grpc.workflow_trl._
import org.eevolution.context.workflow.infrastructure.service.grpc.workflow_trl.ZioWorkflowTrl.RWorkflowTrlService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WorkflowTrl Service Base Trait with the implementation for gRPC WorkflowTrl Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WorkflowTrl Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWorkflowTrlServiceBase {

  class Service[R] extends RWorkflowTrlService[WorkflowTrlService with WorkflowTrlRepository] {

    
    override def getWorkflowTrlAll(request: WorkflowTrlAllRequest): ZStream[
      WorkflowTrlService with WorkflowTrlRepository with Any,
      Status,
      WorkflowTrlStreamResponse
    ] =
      ZStream
        .fromEffect(
          workflowTrlServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              workflowTrlList =>
                WorkflowTrlStreamResponse.of(
                  workflowTrlList.map(workflowTrl => ~>(workflowTrl))
                )
            )
        )
    
    override def getWorkflowTrlAllByClientId(
        request: WorkflowTrlAllByClientIdRequest
    ): ZStream[
      WorkflowTrlService with WorkflowTrlRepository with Any,
      Status,
      WorkflowTrlStreamResponse
    ] =
      ZStream
        .fromEffect(
          workflowTrlServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              workflowTrlList =>
                WorkflowTrlStreamResponse.of(
                  workflowTrlList.map(workflowTrl => ~>(workflowTrl))
                )
            )
        )
    

override def save(request: WorkflowTrlSaveRequest): ZIO[
      WorkflowTrlService with WorkflowTrlRepository with Any,
      Status,
      WorkflowTrlResponse
    ] = {
      val workflowTrl = request.getWorkflowTrl
      workflowTrlServiceApi
        .save(as(workflowTrl))
        .bimap(
          _ => Status.NOT_FOUND,
          workflowTrl => WorkflowTrlResponse(~>?(workflowTrl))
        )
    }
    
    private def ~>?(
        workflowTrl: context.workflow.domain.model.WorkflowTrl
    ): Option[WorkflowTrl] = {
      Option(~>(workflowTrl))
    }

    private def ~>(
        workflowTrl: context.workflow.domain.model.WorkflowTrl
    ): WorkflowTrl = 
      WorkflowTrl(
        workflowTrl.workflowId,
        workflowTrl.language,
        workflowTrl.name,
        workflowTrl.description,
        workflowTrl.help,
        workflowTrl.isTranslated,
        workflowTrl.isActive,
        workflowTrl.created,
        workflowTrl.createdBy,
        workflowTrl.updated,
        workflowTrl.updatedBy,
        workflowTrl.clientId,
        workflowTrl.orgId,
        workflowTrl.uuid)


   private def as(workflowTrl: context.workflow.infrastructure.service.grpc.workflow_trl.WorkflowTrl) = 
      context.workflow.domain.model.WorkflowTrl(
        workflowTrl.workflowId,
        workflowTrl.language,
        workflowTrl.name,
        workflowTrl.description,
        workflowTrl.help,
        workflowTrl.isTranslated,
        workflowTrl.isActive,
        workflowTrl.created,
        workflowTrl.createdBy,
        workflowTrl.updated,
        workflowTrl.updatedBy,
        workflowTrl.clientId,
        workflowTrl.orgId,
        workflowTrl.uuid)

 }
}