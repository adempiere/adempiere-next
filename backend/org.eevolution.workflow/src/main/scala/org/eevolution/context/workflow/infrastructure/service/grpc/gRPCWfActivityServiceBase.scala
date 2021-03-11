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
import org.eevolution.context.workflow.domain.wfActivityRepositoryApi.WfActivityRepository
import org.eevolution.context.workflow.domain.wfActivityServiceApi
import org.eevolution.context.workflow.domain.wfActivityServiceApi.WfActivityService
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_activity._
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_activity.ZioWfActivity.RWfActivityService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WfActivity Service Base Trait with the implementation for gRPC WfActivity Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WfActivity Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWfActivityServiceBase {

  class Service[R] extends RWfActivityService[WfActivityService with WfActivityRepository] {


override def getWfActivityByWfNodeId(
        request: WfActivityByWfNodeIdRequest
    ): ZStream[
      WfActivityService with WfActivityRepository with Any,
      Status,
      WfActivityStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfActivityServiceApi
            .getByWfNodeId(request.wfNodeId)
            .bimap(
              _ => Status.NOT_FOUND,
              wfActivityList =>
                WfActivityStreamResponse(
                  wfActivityList.map(wfActivity => ~>(wfActivity))
                )
            )
        )



    override def getWfActivityByWfActivityId(request: WfActivityByWfActivityIdRequest): ZIO[
      WfActivityService with WfActivityRepository with Any,
      Status,
      WfActivityResponse
    ] =
      wfActivityServiceApi
        .getByWfActivityId(request.wfActivityId)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfActivity => {
            val wfActivity = maybeWfActivity.get
            WfActivityResponse(
              Option(
                ~>(wfActivity)
              )
            )
          }
        )


    
    override def getWfActivityAll(request: WfActivityAllRequest): ZStream[
      WfActivityService with WfActivityRepository with Any,
      Status,
      WfActivityStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfActivityServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              wfActivityList =>
                WfActivityStreamResponse.of(
                  wfActivityList.map(wfActivity => ~>(wfActivity))
                )
            )
        )
    
    override def getWfActivityAllByClientId(
        request: WfActivityAllByClientIdRequest
    ): ZStream[
      WfActivityService with WfActivityRepository with Any,
      Status,
      WfActivityStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfActivityServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              wfActivityList =>
                WfActivityStreamResponse.of(
                  wfActivityList.map(wfActivity => ~>(wfActivity))
                )
            )
        )
    

override def save(request: WfActivitySaveRequest): ZIO[
      WfActivityService with WfActivityRepository with Any,
      Status,
      WfActivityResponse
    ] = {
      val wfActivity = request.getWfActivity
      wfActivityServiceApi
        .save(as(wfActivity))
        .bimap(
          _ => Status.NOT_FOUND,
          wfActivity => WfActivityResponse(~>?(wfActivity))
        )
    }
    
    private def ~>?(
        wfActivity: context.workflow.domain.model.WfActivity
    ): Option[WfActivity] = {
      Option(~>(wfActivity))
    }

    private def ~>(
        wfActivity: context.workflow.domain.model.WfActivity
    ): WfActivity = 
      WfActivity(
        wfActivity.wfNodeId,
        wfActivity.wfResponsibleId,
        wfActivity.orgId,
        wfActivity.createdBy,
        wfActivity.userId,
        wfActivity.wfActivityId,
        wfActivity.wfProcessId,
        wfActivity.isActive,
        wfActivity.processed,
        wfActivity.processing,
        wfActivity.wfState,
        wfActivity.created,
        wfActivity.updatedBy,
        wfActivity.clientId,
        wfActivity.messageId,
        wfActivity.updated,
        wfActivity.textMsg,
        wfActivity.workflowId,
        wfActivity.priority,
        wfActivity.recordId,
        wfActivity.tableId,
        wfActivity.endWaitTime,
        wfActivity.dateLastAlert,
        wfActivity.dynPriorityStart,
        wfActivity.uuid)


   private def as(wfActivity: context.workflow.infrastructure.service.grpc.wf_activity.WfActivity) = 
      context.workflow.domain.model.WfActivity(
        wfActivity.wfNodeId,
        wfActivity.wfResponsibleId,
        wfActivity.orgId,
        wfActivity.createdBy,
        wfActivity.userId,
        wfActivity.wfActivityId,
        wfActivity.wfProcessId,
        wfActivity.isActive,
        wfActivity.processed,
        wfActivity.processing,
        wfActivity.wfState,
        wfActivity.created,
        wfActivity.updatedBy,
        wfActivity.clientId,
        wfActivity.messageId,
        wfActivity.updated,
        wfActivity.textMsg,
        wfActivity.workflowId,
        wfActivity.priority,
        wfActivity.recordId,
        wfActivity.tableId,
        wfActivity.endWaitTime,
        wfActivity.dateLastAlert,
        wfActivity.dynPriorityStart,
        wfActivity.uuid)

 }
}