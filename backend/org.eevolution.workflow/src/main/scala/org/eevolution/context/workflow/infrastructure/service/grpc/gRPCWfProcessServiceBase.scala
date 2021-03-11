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
import org.eevolution.context.workflow.domain.wfProcessRepositoryApi.WfProcessRepository
import org.eevolution.context.workflow.domain.wfProcessServiceApi
import org.eevolution.context.workflow.domain.wfProcessServiceApi.WfProcessService
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_process._
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_process.ZioWfProcess.RWfProcessService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WfProcess Service Base Trait with the implementation for gRPC WfProcess Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WfProcess Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWfProcessServiceBase {

  class Service[R] extends RWfProcessService[WfProcessService with WfProcessRepository] {


override def getWfProcessByWorkflowId(
        request: WfProcessByWorkflowIdRequest
    ): ZStream[
      WfProcessService with WfProcessRepository with Any,
      Status,
      WfProcessStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfProcessServiceApi
            .getByWorkflowId(request.workflowId)
            .bimap(
              _ => Status.NOT_FOUND,
              wfProcessList =>
                WfProcessStreamResponse(
                  wfProcessList.map(wfProcess => ~>(wfProcess))
                )
            )
        )



    override def getWfProcessByWfProcessId(request: WfProcessByWfProcessIdRequest): ZIO[
      WfProcessService with WfProcessRepository with Any,
      Status,
      WfProcessResponse
    ] =
      wfProcessServiceApi
        .getByWfProcessId(request.wfProcessId)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfProcess => {
            val wfProcess = maybeWfProcess.get
            WfProcessResponse(
              Option(
                ~>(wfProcess)
              )
            )
          }
        )


    
    override def getWfProcessAll(request: WfProcessAllRequest): ZStream[
      WfProcessService with WfProcessRepository with Any,
      Status,
      WfProcessStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfProcessServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              wfProcessList =>
                WfProcessStreamResponse.of(
                  wfProcessList.map(wfProcess => ~>(wfProcess))
                )
            )
        )
    
    override def getWfProcessAllByClientId(
        request: WfProcessAllByClientIdRequest
    ): ZStream[
      WfProcessService with WfProcessRepository with Any,
      Status,
      WfProcessStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfProcessServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              wfProcessList =>
                WfProcessStreamResponse.of(
                  wfProcessList.map(wfProcess => ~>(wfProcess))
                )
            )
        )
    

override def save(request: WfProcessSaveRequest): ZIO[
      WfProcessService with WfProcessRepository with Any,
      Status,
      WfProcessResponse
    ] = {
      val wfProcess = request.getWfProcess
      wfProcessServiceApi
        .save(as(wfProcess))
        .bimap(
          _ => Status.NOT_FOUND,
          wfProcess => WfProcessResponse(~>?(wfProcess))
        )
    }
    
    private def ~>?(
        wfProcess: context.workflow.domain.model.WfProcess
    ): Option[WfProcess] = {
      Option(~>(wfProcess))
    }

    private def ~>(
        wfProcess: context.workflow.domain.model.WfProcess
    ): WfProcess = 
      WfProcess(
        wfProcess.created,
        wfProcess.processing,
        wfProcess.updated,
        wfProcess.processed,
        wfProcess.isActive,
        wfProcess.workflowId,
        wfProcess.clientId,
        wfProcess.createdBy,
        wfProcess.orgId,
        wfProcess.wfState,
        wfProcess.userId,
        wfProcess.updatedBy,
        wfProcess.wfResponsibleId,
        wfProcess.wfProcessId,
        wfProcess.messageId,
        wfProcess.textMsg,
        wfProcess.tableId,
        wfProcess.priority,
        wfProcess.recordId,
        wfProcess.uuid)


   private def as(wfProcess: context.workflow.infrastructure.service.grpc.wf_process.WfProcess) = 
      context.workflow.domain.model.WfProcess(
        wfProcess.created,
        wfProcess.processing,
        wfProcess.updated,
        wfProcess.processed,
        wfProcess.isActive,
        wfProcess.workflowId,
        wfProcess.clientId,
        wfProcess.createdBy,
        wfProcess.orgId,
        wfProcess.wfState,
        wfProcess.userId,
        wfProcess.updatedBy,
        wfProcess.wfResponsibleId,
        wfProcess.wfProcessId,
        wfProcess.messageId,
        wfProcess.textMsg,
        wfProcess.tableId,
        wfProcess.priority,
        wfProcess.recordId,
        wfProcess.uuid)

 }
}