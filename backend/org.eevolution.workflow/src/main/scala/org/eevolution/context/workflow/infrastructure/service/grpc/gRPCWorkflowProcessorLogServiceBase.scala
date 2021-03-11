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
import org.eevolution.context.workflow.domain.workflowProcessorLogRepositoryApi.WorkflowProcessorLogRepository
import org.eevolution.context.workflow.domain.workflowProcessorLogServiceApi
import org.eevolution.context.workflow.domain.workflowProcessorLogServiceApi.WorkflowProcessorLogService
import org.eevolution.context.workflow.infrastructure.service.grpc.workflow_processor_log._
import org.eevolution.context.workflow.infrastructure.service.grpc.workflow_processor_log.ZioWorkflowProcessorLog.RWorkflowProcessorLogService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WorkflowProcessorLog Service Base Trait with the implementation for gRPC WorkflowProcessorLog Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WorkflowProcessorLog Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWorkflowProcessorLogServiceBase {

  class Service[R] extends RWorkflowProcessorLogService[WorkflowProcessorLogService with WorkflowProcessorLogRepository] {


override def getWorkflowProcessorLogByWorkflowProcessorId(
        request: WorkflowProcessorLogByWorkflowProcessorIdRequest
    ): ZStream[
      WorkflowProcessorLogService with WorkflowProcessorLogRepository with Any,
      Status,
      WorkflowProcessorLogStreamResponse
    ] =
      ZStream
        .fromEffect(
          workflowProcessorLogServiceApi
            .getByWorkflowProcessorId(request.workflowProcessorId)
            .bimap(
              _ => Status.NOT_FOUND,
              workflowProcessorLogList =>
                WorkflowProcessorLogStreamResponse(
                  workflowProcessorLogList.map(workflowProcessorLog => ~>(workflowProcessorLog))
                )
            )
        )



    override def getWorkflowProcessorLogByWorkflowProcessorLogId(request: WorkflowProcessorLogByWorkflowProcessorLogIdRequest): ZIO[
      WorkflowProcessorLogService with WorkflowProcessorLogRepository with Any,
      Status,
      WorkflowProcessorLogResponse
    ] =
      workflowProcessorLogServiceApi
        .getByWorkflowProcessorLogId(request.workflowProcessorLogId)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWorkflowProcessorLog => {
            val workflowProcessorLog = maybeWorkflowProcessorLog.get
            WorkflowProcessorLogResponse(
              Option(
                ~>(workflowProcessorLog)
              )
            )
          }
        )



override def getWorkflowProcessorLogByDescription(
        request: WorkflowProcessorLogByDescriptionRequest
    ): ZStream[
      WorkflowProcessorLogService with WorkflowProcessorLogRepository with Any,
      Status,
      WorkflowProcessorLogStreamResponse
    ] =
      ZStream
        .fromEffect(
          workflowProcessorLogServiceApi
            .getByDescription(request.description)
            .bimap(
              _ => Status.NOT_FOUND,
              workflowProcessorLogList =>
                WorkflowProcessorLogStreamResponse(
                  workflowProcessorLogList.map(workflowProcessorLog => ~>(workflowProcessorLog))
                )
            )
        )


    
    override def getWorkflowProcessorLogAll(request: WorkflowProcessorLogAllRequest): ZStream[
      WorkflowProcessorLogService with WorkflowProcessorLogRepository with Any,
      Status,
      WorkflowProcessorLogStreamResponse
    ] =
      ZStream
        .fromEffect(
          workflowProcessorLogServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              workflowProcessorLogList =>
                WorkflowProcessorLogStreamResponse.of(
                  workflowProcessorLogList.map(workflowProcessorLog => ~>(workflowProcessorLog))
                )
            )
        )
    
    override def getWorkflowProcessorLogAllByClientId(
        request: WorkflowProcessorLogAllByClientIdRequest
    ): ZStream[
      WorkflowProcessorLogService with WorkflowProcessorLogRepository with Any,
      Status,
      WorkflowProcessorLogStreamResponse
    ] =
      ZStream
        .fromEffect(
          workflowProcessorLogServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              workflowProcessorLogList =>
                WorkflowProcessorLogStreamResponse.of(
                  workflowProcessorLogList.map(workflowProcessorLog => ~>(workflowProcessorLog))
                )
            )
        )
    

override def save(request: WorkflowProcessorLogSaveRequest): ZIO[
      WorkflowProcessorLogService with WorkflowProcessorLogRepository with Any,
      Status,
      WorkflowProcessorLogResponse
    ] = {
      val workflowProcessorLog = request.getWorkflowProcessorLog
      workflowProcessorLogServiceApi
        .save(as(workflowProcessorLog))
        .bimap(
          _ => Status.NOT_FOUND,
          workflowProcessorLog => WorkflowProcessorLogResponse(~>?(workflowProcessorLog))
        )
    }
    
    private def ~>?(
        workflowProcessorLog: context.workflow.domain.model.WorkflowProcessorLog
    ): Option[WorkflowProcessorLog] = {
      Option(~>(workflowProcessorLog))
    }

    private def ~>(
        workflowProcessorLog: context.workflow.domain.model.WorkflowProcessorLog
    ): WorkflowProcessorLog = 
      WorkflowProcessorLog(
        workflowProcessorLog.orgId,
        workflowProcessorLog.clientId,
        workflowProcessorLog.isActive,
        workflowProcessorLog.workflowProcessorId,
        workflowProcessorLog.workflowProcessorLogId,
        workflowProcessorLog.binaryData,
        workflowProcessorLog.updated,
        workflowProcessorLog.updatedBy,
        workflowProcessorLog.created,
        workflowProcessorLog.summary,
        workflowProcessorLog.textMsg,
        workflowProcessorLog.reference,
        workflowProcessorLog.description,
        workflowProcessorLog.isError,
        workflowProcessorLog.createdBy,
        workflowProcessorLog.uuid)


   private def as(workflowProcessorLog: context.workflow.infrastructure.service.grpc.workflow_processor_log.WorkflowProcessorLog) = 
      context.workflow.domain.model.WorkflowProcessorLog(
        workflowProcessorLog.orgId,
        workflowProcessorLog.clientId,
        workflowProcessorLog.isActive,
        workflowProcessorLog.workflowProcessorId,
        workflowProcessorLog.workflowProcessorLogId,
        workflowProcessorLog.binaryData,
        workflowProcessorLog.updated,
        workflowProcessorLog.updatedBy,
        workflowProcessorLog.created,
        workflowProcessorLog.summary,
        workflowProcessorLog.textMsg,
        workflowProcessorLog.reference,
        workflowProcessorLog.description,
        workflowProcessorLog.isError,
        workflowProcessorLog.createdBy,
        workflowProcessorLog.uuid)

 }
}