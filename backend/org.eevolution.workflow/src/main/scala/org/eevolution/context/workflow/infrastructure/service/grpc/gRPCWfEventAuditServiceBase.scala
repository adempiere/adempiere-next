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
import org.eevolution.context.workflow.domain.wfEventAuditRepositoryApi.WfEventAuditRepository
import org.eevolution.context.workflow.domain.wfEventAuditServiceApi
import org.eevolution.context.workflow.domain.wfEventAuditServiceApi.WfEventAuditService
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_event_audit._
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_event_audit.ZioWfEventAudit.RWfEventAuditService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WfEventAudit Service Base Trait with the implementation for gRPC WfEventAudit Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WfEventAudit Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWfEventAuditServiceBase {

  class Service[R] extends RWfEventAuditService[WfEventAuditService with WfEventAuditRepository] {


override def getWfEventAuditByAttributeName(
        request: WfEventAuditByAttributeNameRequest
    ): ZStream[
      WfEventAuditService with WfEventAuditRepository with Any,
      Status,
      WfEventAuditStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfEventAuditServiceApi
            .getByAttributeName(request.attributeName)
            .bimap(
              _ => Status.NOT_FOUND,
              wfEventAuditList =>
                WfEventAuditStreamResponse(
                  wfEventAuditList.map(wfEventAudit => ~>(wfEventAudit))
                )
            )
        )



    override def getWfEventAuditByWfEventAuditId(request: WfEventAuditByWfEventAuditIdRequest): ZIO[
      WfEventAuditService with WfEventAuditRepository with Any,
      Status,
      WfEventAuditResponse
    ] =
      wfEventAuditServiceApi
        .getByWfEventAuditId(request.wfEventAuditId)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfEventAudit => {
            val wfEventAudit = maybeWfEventAudit.get
            WfEventAuditResponse(
              Option(
                ~>(wfEventAudit)
              )
            )
          }
        )



override def getWfEventAuditByDescription(
        request: WfEventAuditByDescriptionRequest
    ): ZStream[
      WfEventAuditService with WfEventAuditRepository with Any,
      Status,
      WfEventAuditStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfEventAuditServiceApi
            .getByDescription(request.description)
            .bimap(
              _ => Status.NOT_FOUND,
              wfEventAuditList =>
                WfEventAuditStreamResponse(
                  wfEventAuditList.map(wfEventAudit => ~>(wfEventAudit))
                )
            )
        )


    
    override def getWfEventAuditAll(request: WfEventAuditAllRequest): ZStream[
      WfEventAuditService with WfEventAuditRepository with Any,
      Status,
      WfEventAuditStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfEventAuditServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              wfEventAuditList =>
                WfEventAuditStreamResponse.of(
                  wfEventAuditList.map(wfEventAudit => ~>(wfEventAudit))
                )
            )
        )
    
    override def getWfEventAuditAllByClientId(
        request: WfEventAuditAllByClientIdRequest
    ): ZStream[
      WfEventAuditService with WfEventAuditRepository with Any,
      Status,
      WfEventAuditStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfEventAuditServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              wfEventAuditList =>
                WfEventAuditStreamResponse.of(
                  wfEventAuditList.map(wfEventAudit => ~>(wfEventAudit))
                )
            )
        )
    

override def save(request: WfEventAuditSaveRequest): ZIO[
      WfEventAuditService with WfEventAuditRepository with Any,
      Status,
      WfEventAuditResponse
    ] = {
      val wfEventAudit = request.getWfEventAudit
      wfEventAuditServiceApi
        .save(as(wfEventAudit))
        .bimap(
          _ => Status.NOT_FOUND,
          wfEventAudit => WfEventAuditResponse(~>?(wfEventAudit))
        )
    }
    
    private def ~>?(
        wfEventAudit: context.workflow.domain.model.WfEventAudit
    ): Option[WfEventAudit] = {
      Option(~>(wfEventAudit))
    }

    private def ~>(
        wfEventAudit: context.workflow.domain.model.WfEventAudit
    ): WfEventAudit = 
      WfEventAudit(
        wfEventAudit.attributeName,
        wfEventAudit.userId,
        wfEventAudit.wfResponsibleId,
        wfEventAudit.clientId,
        wfEventAudit.isActive,
        wfEventAudit.updatedBy,
        wfEventAudit.newValue,
        wfEventAudit.orgId,
        wfEventAudit.updated,
        wfEventAudit.createdBy,
        wfEventAudit.wfState,
        wfEventAudit.created,
        wfEventAudit.oldValue,
        wfEventAudit.wfEventAuditId,
        wfEventAudit.eventType,
        wfEventAudit.description,
        wfEventAudit.wfProcessId,
        wfEventAudit.tableId,
        wfEventAudit.textMsg,
        wfEventAudit.wfNodeId,
        wfEventAudit.recordId,
        wfEventAudit.elapsedTimeMS,
        wfEventAudit.uuid)


   private def as(wfEventAudit: context.workflow.infrastructure.service.grpc.wf_event_audit.WfEventAudit) = 
      context.workflow.domain.model.WfEventAudit(
        wfEventAudit.attributeName,
        wfEventAudit.userId,
        wfEventAudit.wfResponsibleId,
        wfEventAudit.clientId,
        wfEventAudit.isActive,
        wfEventAudit.updatedBy,
        wfEventAudit.newValue,
        wfEventAudit.orgId,
        wfEventAudit.updated,
        wfEventAudit.createdBy,
        wfEventAudit.wfState,
        wfEventAudit.created,
        wfEventAudit.oldValue,
        wfEventAudit.wfEventAuditId,
        wfEventAudit.eventType,
        wfEventAudit.description,
        wfEventAudit.wfProcessId,
        wfEventAudit.tableId,
        wfEventAudit.textMsg,
        wfEventAudit.wfNodeId,
        wfEventAudit.recordId,
        wfEventAudit.elapsedTimeMS,
        wfEventAudit.uuid)

 }
}