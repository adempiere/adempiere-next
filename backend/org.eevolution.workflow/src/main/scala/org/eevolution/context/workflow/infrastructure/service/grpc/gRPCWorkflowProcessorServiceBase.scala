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
import org.eevolution.context.workflow.domain.workflowProcessorRepositoryApi.WorkflowProcessorRepository
import org.eevolution.context.workflow.domain.workflowProcessorServiceApi
import org.eevolution.context.workflow.domain.workflowProcessorServiceApi.WorkflowProcessorService
import org.eevolution.context.workflow.infrastructure.service.grpc.workflow_processor._
import org.eevolution.context.workflow.infrastructure.service.grpc.workflow_processor.ZioWorkflowProcessor.RWorkflowProcessorService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WorkflowProcessor Service Base Trait with the implementation for gRPC WorkflowProcessor Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WorkflowProcessor Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWorkflowProcessorServiceBase {

  class Service[R] extends RWorkflowProcessorService[WorkflowProcessorService with WorkflowProcessorRepository] {


override def getWorkflowProcessorByDescription(
        request: WorkflowProcessorByDescriptionRequest
    ): ZStream[
      WorkflowProcessorService with WorkflowProcessorRepository with Any,
      Status,
      WorkflowProcessorStreamResponse
    ] =
      ZStream
        .fromEffect(
          workflowProcessorServiceApi
            .getByDescription(request.description)
            .bimap(
              _ => Status.NOT_FOUND,
              workflowProcessorList =>
                WorkflowProcessorStreamResponse(
                  workflowProcessorList.map(workflowProcessor => ~>(workflowProcessor))
                )
            )
        )



    override def getWorkflowProcessorByName(request: WorkflowProcessorByNameRequest): ZIO[
      WorkflowProcessorService with WorkflowProcessorRepository with Any,
      Status,
      WorkflowProcessorResponse
    ] =
      workflowProcessorServiceApi
        .getByName(request.name)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWorkflowProcessor => {
            val workflowProcessor = maybeWorkflowProcessor.get
            WorkflowProcessorResponse(
              Option(
                ~>(workflowProcessor)
              )
            )
          }
        )



    override def getWorkflowProcessorByWorkflowProcessorId(request: WorkflowProcessorByWorkflowProcessorIdRequest): ZIO[
      WorkflowProcessorService with WorkflowProcessorRepository with Any,
      Status,
      WorkflowProcessorResponse
    ] =
      workflowProcessorServiceApi
        .getByWorkflowProcessorId(request.workflowProcessorId)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWorkflowProcessor => {
            val workflowProcessor = maybeWorkflowProcessor.get
            WorkflowProcessorResponse(
              Option(
                ~>(workflowProcessor)
              )
            )
          }
        )


    
    override def getWorkflowProcessorAll(request: WorkflowProcessorAllRequest): ZStream[
      WorkflowProcessorService with WorkflowProcessorRepository with Any,
      Status,
      WorkflowProcessorStreamResponse
    ] =
      ZStream
        .fromEffect(
          workflowProcessorServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              workflowProcessorList =>
                WorkflowProcessorStreamResponse.of(
                  workflowProcessorList.map(workflowProcessor => ~>(workflowProcessor))
                )
            )
        )
    
    override def getWorkflowProcessorAllByClientId(
        request: WorkflowProcessorAllByClientIdRequest
    ): ZStream[
      WorkflowProcessorService with WorkflowProcessorRepository with Any,
      Status,
      WorkflowProcessorStreamResponse
    ] =
      ZStream
        .fromEffect(
          workflowProcessorServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              workflowProcessorList =>
                WorkflowProcessorStreamResponse.of(
                  workflowProcessorList.map(workflowProcessor => ~>(workflowProcessor))
                )
            )
        )
    

override def save(request: WorkflowProcessorSaveRequest): ZIO[
      WorkflowProcessorService with WorkflowProcessorRepository with Any,
      Status,
      WorkflowProcessorResponse
    ] = {
      val workflowProcessor = request.getWorkflowProcessor
      workflowProcessorServiceApi
        .save(as(workflowProcessor))
        .bimap(
          _ => Status.NOT_FOUND,
          workflowProcessor => WorkflowProcessorResponse(~>?(workflowProcessor))
        )
    }
    
    private def ~>?(
        workflowProcessor: context.workflow.domain.model.WorkflowProcessor
    ): Option[WorkflowProcessor] = {
      Option(~>(workflowProcessor))
    }

    private def ~>(
        workflowProcessor: context.workflow.domain.model.WorkflowProcessor
    ): WorkflowProcessor = 
      WorkflowProcessor(
        workflowProcessor.dateNextRun,
        workflowProcessor.description,
        workflowProcessor.keepLogDays,
        workflowProcessor.frequencyType,
        workflowProcessor.name,
        workflowProcessor.processing,
        workflowProcessor.updatedBy,
        workflowProcessor.clientId,
        workflowProcessor.orgId,
        workflowProcessor.workflowProcessorId,
        workflowProcessor.isActive,
        workflowProcessor.createdBy,
        workflowProcessor.frequency,
        workflowProcessor.updated,
        workflowProcessor.created,
        workflowProcessor.supervisorId,
        workflowProcessor.dateLastRun,
        workflowProcessor.inactivityAlertDays,
        workflowProcessor.remindDays,
        workflowProcessor.alertOverPriority,
        workflowProcessor.uuid)


   private def as(workflowProcessor: context.workflow.infrastructure.service.grpc.workflow_processor.WorkflowProcessor) = 
      context.workflow.domain.model.WorkflowProcessor(
        workflowProcessor.dateNextRun,
        workflowProcessor.description,
        workflowProcessor.keepLogDays,
        workflowProcessor.frequencyType,
        workflowProcessor.name,
        workflowProcessor.processing,
        workflowProcessor.updatedBy,
        workflowProcessor.clientId,
        workflowProcessor.orgId,
        workflowProcessor.workflowProcessorId,
        workflowProcessor.isActive,
        workflowProcessor.createdBy,
        workflowProcessor.frequency,
        workflowProcessor.updated,
        workflowProcessor.created,
        workflowProcessor.supervisorId,
        workflowProcessor.dateLastRun,
        workflowProcessor.inactivityAlertDays,
        workflowProcessor.remindDays,
        workflowProcessor.alertOverPriority,
        workflowProcessor.uuid)

 }
}