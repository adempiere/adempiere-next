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
import org.eevolution.context.workflow.domain.workflowRepositoryApi.WorkflowRepository
import org.eevolution.context.workflow.domain.workflowServiceApi
import org.eevolution.context.workflow.domain.workflowServiceApi.WorkflowService
import org.eevolution.context.workflow.infrastructure.service.grpc.workflow._
import org.eevolution.context.workflow.infrastructure.service.grpc.workflow.ZioWorkflow.RWorkflowService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC Workflow Service Base Trait with the implementation for gRPC Workflow Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC Workflow Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWorkflowServiceBase {

  class Service[R] extends RWorkflowService[WorkflowService with WorkflowRepository] {


    override def getWorkflowByName(request: WorkflowByNameRequest): ZIO[
      WorkflowService with WorkflowRepository with Any,
      Status,
      WorkflowResponse
    ] =
      workflowServiceApi
        .getByName(request.name)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWorkflow => {
            val workflow = maybeWorkflow.get
            WorkflowResponse(
              Option(
                ~>(workflow)
              )
            )
          }
        )



override def getWorkflowByDescription(
        request: WorkflowByDescriptionRequest
    ): ZStream[
      WorkflowService with WorkflowRepository with Any,
      Status,
      WorkflowStreamResponse
    ] =
      ZStream
        .fromEffect(
          workflowServiceApi
            .getByDescription(request.description)
            .bimap(
              _ => Status.NOT_FOUND,
              workflowList =>
                WorkflowStreamResponse(
                  workflowList.map(workflow => ~>(workflow))
                )
            )
        )



    override def getWorkflowByWorkflowId(request: WorkflowByWorkflowIdRequest): ZIO[
      WorkflowService with WorkflowRepository with Any,
      Status,
      WorkflowResponse
    ] =
      workflowServiceApi
        .getByWorkflowId(request.workflowId)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWorkflow => {
            val workflow = maybeWorkflow.get
            WorkflowResponse(
              Option(
                ~>(workflow)
              )
            )
          }
        )



    override def getWorkflowByValue(request: WorkflowByValueRequest): ZIO[
      WorkflowService with WorkflowRepository with Any,
      Status,
      WorkflowResponse
    ] =
      workflowServiceApi
        .getByValue(request.value)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWorkflow => {
            val workflow = maybeWorkflow.get
            WorkflowResponse(
              Option(
                ~>(workflow)
              )
            )
          }
        )



override def getWorkflowByDocumentNo(
        request: WorkflowByDocumentNoRequest
    ): ZStream[
      WorkflowService with WorkflowRepository with Any,
      Status,
      WorkflowStreamResponse
    ] =
      ZStream
        .fromEffect(
          workflowServiceApi
            .getByDocumentNo(request.documentNo)
            .bimap(
              _ => Status.NOT_FOUND,
              workflowList =>
                WorkflowStreamResponse(
                  workflowList.map(workflow => ~>(workflow))
                )
            )
        )


    
    override def getWorkflowAll(request: WorkflowAllRequest): ZStream[
      WorkflowService with WorkflowRepository with Any,
      Status,
      WorkflowStreamResponse
    ] =
      ZStream
        .fromEffect(
          workflowServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              workflowList =>
                WorkflowStreamResponse.of(
                  workflowList.map(workflow => ~>(workflow))
                )
            )
        )
    
    override def getWorkflowAllByClientId(
        request: WorkflowAllByClientIdRequest
    ): ZStream[
      WorkflowService with WorkflowRepository with Any,
      Status,
      WorkflowStreamResponse
    ] =
      ZStream
        .fromEffect(
          workflowServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              workflowList =>
                WorkflowStreamResponse.of(
                  workflowList.map(workflow => ~>(workflow))
                )
            )
        )
    

override def save(request: WorkflowSaveRequest): ZIO[
      WorkflowService with WorkflowRepository with Any,
      Status,
      WorkflowResponse
    ] = {
      val workflow = request.getWorkflow
      workflowServiceApi
        .save(as(workflow))
        .bimap(
          _ => Status.NOT_FOUND,
          workflow => WorkflowResponse(~>?(workflow))
        )
    }
    
    private def ~>?(
        workflow: context.workflow.domain.model.Workflow
    ): Option[Workflow] = {
      Option(~>(workflow))
    }

    private def ~>(
        workflow: context.workflow.domain.model.Workflow
    ): Workflow = 
      Workflow(
        workflow.name,
        workflow.description,
        workflow.help,
        workflow.workflowId,
        workflow.clientId,
        workflow.orgId,
        workflow.isActive,
        workflow.created,
        workflow.createdBy,
        workflow.updated,
        workflow.updatedBy,
        workflow.wfNodeId,
        workflow.accessLevel,
        workflow.entityType,
        workflow.author,
        workflow.priority,
        workflow.workingTime,
        workflow.limit,
        workflow.validFrom,
        workflow.duration,
        workflow.wfResponsibleId,
        workflow.version,
        workflow.cost,
        workflow.validTo,
        workflow.durationUnit,
        workflow.waitingTime,
        workflow.publishStatus,
        workflow.workflowProcessorId,
        workflow.isDefault,
        workflow.validateWorkflow,
        workflow.tableId,
        workflow.value,
        workflow.workflowType,
        workflow.docValueLogic,
        workflow.isValid,
        workflow.resourceId,
        workflow.setupTime,
        workflow.movingTime,
        workflow.processType,
        workflow.documentNo,
        workflow.qtyBatchSize,
        workflow.queuingTime,
        workflow.isBetaFunctionality,
        workflow.`yield`,
        workflow.unitsCycles,
        workflow.overlapUnits,
        workflow.uuid)


   private def as(workflow: context.workflow.infrastructure.service.grpc.workflow.Workflow) = 
      context.workflow.domain.model.Workflow(
        workflow.name,
        workflow.description,
        workflow.help,
        workflow.workflowId,
        workflow.clientId,
        workflow.orgId,
        workflow.isActive,
        workflow.created,
        workflow.createdBy,
        workflow.updated,
        workflow.updatedBy,
        workflow.wfNodeId,
        workflow.accessLevel,
        workflow.entityType,
        workflow.author,
        workflow.priority,
        workflow.workingTime,
        workflow.limit,
        workflow.validFrom,
        workflow.duration,
        workflow.wfResponsibleId,
        workflow.version,
        workflow.cost,
        workflow.validTo,
        workflow.durationUnit,
        workflow.waitingTime,
        workflow.publishStatus,
        workflow.workflowProcessorId,
        workflow.isDefault,
        workflow.validateWorkflow,
        workflow.tableId,
        workflow.value,
        workflow.workflowType,
        workflow.docValueLogic,
        workflow.isValid,
        workflow.resourceId,
        workflow.setupTime,
        workflow.movingTime,
        workflow.processType,
        workflow.documentNo,
        workflow.qtyBatchSize,
        workflow.queuingTime,
        workflow.isBetaFunctionality,
        workflow.`yield`,
        workflow.unitsCycles,
        workflow.overlapUnits,
        workflow.uuid)

 }
}