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
import org.eevolution.context.workflow.domain.wfNodeRepositoryApi.WfNodeRepository
import org.eevolution.context.workflow.domain.wfNodeServiceApi
import org.eevolution.context.workflow.domain.wfNodeServiceApi.WfNodeService
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_node._
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_node.ZioWfNode.RWfNodeService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WfNode Service Base Trait with the implementation for gRPC WfNode Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WfNode Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWfNodeServiceBase {

  class Service[R] extends RWfNodeService[WfNodeService with WfNodeRepository] {


    override def getWfNodeByWfNodeId(request: WfNodeByWfNodeIdRequest): ZIO[
      WfNodeService with WfNodeRepository with Any,
      Status,
      WfNodeResponse
    ] =
      wfNodeServiceApi
        .getByWfNodeId(request.wfNodeId)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfNode => {
            val wfNode = maybeWfNode.get
            WfNodeResponse(
              Option(
                ~>(wfNode)
              )
            )
          }
        )



    override def getWfNodeByName(request: WfNodeByNameRequest): ZIO[
      WfNodeService with WfNodeRepository with Any,
      Status,
      WfNodeResponse
    ] =
      wfNodeServiceApi
        .getByName(request.name)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfNode => {
            val wfNode = maybeWfNode.get
            WfNodeResponse(
              Option(
                ~>(wfNode)
              )
            )
          }
        )



override def getWfNodeByDescription(
        request: WfNodeByDescriptionRequest
    ): ZStream[
      WfNodeService with WfNodeRepository with Any,
      Status,
      WfNodeStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeServiceApi
            .getByDescription(request.description)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeList =>
                WfNodeStreamResponse(
                  wfNodeList.map(wfNode => ~>(wfNode))
                )
            )
        )



override def getWfNodeByWorkflowId(
        request: WfNodeByWorkflowIdRequest
    ): ZStream[
      WfNodeService with WfNodeRepository with Any,
      Status,
      WfNodeStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeServiceApi
            .getByWorkflowId(request.workflowId)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeList =>
                WfNodeStreamResponse(
                  wfNodeList.map(wfNode => ~>(wfNode))
                )
            )
        )



override def getWfNodeByAttributeName(
        request: WfNodeByAttributeNameRequest
    ): ZStream[
      WfNodeService with WfNodeRepository with Any,
      Status,
      WfNodeStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeServiceApi
            .getByAttributeName(request.attributeName)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeList =>
                WfNodeStreamResponse(
                  wfNodeList.map(wfNode => ~>(wfNode))
                )
            )
        )



    override def getWfNodeByValue(request: WfNodeByValueRequest): ZIO[
      WfNodeService with WfNodeRepository with Any,
      Status,
      WfNodeResponse
    ] =
      wfNodeServiceApi
        .getByValue(request.value)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfNode => {
            val wfNode = maybeWfNode.get
            WfNodeResponse(
              Option(
                ~>(wfNode)
              )
            )
          }
        )


    
    override def getWfNodeAll(request: WfNodeAllRequest): ZStream[
      WfNodeService with WfNodeRepository with Any,
      Status,
      WfNodeStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeList =>
                WfNodeStreamResponse.of(
                  wfNodeList.map(wfNode => ~>(wfNode))
                )
            )
        )
    
    override def getWfNodeAllByClientId(
        request: WfNodeAllByClientIdRequest
    ): ZStream[
      WfNodeService with WfNodeRepository with Any,
      Status,
      WfNodeStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeList =>
                WfNodeStreamResponse.of(
                  wfNodeList.map(wfNode => ~>(wfNode))
                )
            )
        )
    

override def save(request: WfNodeSaveRequest): ZIO[
      WfNodeService with WfNodeRepository with Any,
      Status,
      WfNodeResponse
    ] = {
      val wfNode = request.getWfNode
      wfNodeServiceApi
        .save(as(wfNode))
        .bimap(
          _ => Status.NOT_FOUND,
          wfNode => WfNodeResponse(~>?(wfNode))
        )
    }
    
    private def ~>?(
        wfNode: context.workflow.domain.model.WfNode
    ): Option[WfNode] = {
      Option(~>(wfNode))
    }

    private def ~>(
        wfNode: context.workflow.domain.model.WfNode
    ): WfNode = 
      WfNode(
        wfNode.wfNodeId,
        wfNode.name,
        wfNode.description,
        wfNode.workflowId,
        wfNode.clientId,
        wfNode.orgId,
        wfNode.isActive,
        wfNode.created,
        wfNode.createdBy,
        wfNode.updated,
        wfNode.updatedBy,
        wfNode.help,
        wfNode.action,
        wfNode.windowId,
        wfNode.workflowId,
        wfNode.taskId,
        wfNode.processId,
        wfNode.formId,
        wfNode.isCentrallyMaintained,
        wfNode.yPosition,
        wfNode.entityType,
        wfNode.xPosition,
        wfNode.limit,
        wfNode.startMode,
        wfNode.wfResponsibleId,
        wfNode.duration,
        wfNode.subflowExecution,
        wfNode.cost,
        wfNode.waitingTime,
        wfNode.workingTime,
        wfNode.wfBlockId,
        wfNode.priority,
        wfNode.finishMode,
        wfNode.joinElement,
        wfNode.splitElement,
        wfNode.imageId,
        wfNode.columnId,
        wfNode.waitTime,
        wfNode.attributeValue,
        wfNode.attributeName,
        wfNode.docAction,
        wfNode.value,
        wfNode.dynPriorityUnit,
        wfNode.dynPriorityChange,
        wfNode.eMailRecipient,
        wfNode.eMail,
        wfNode.mailTextId,
        wfNode.validTo,
        wfNode.isMilestone,
        wfNode.isSubcontracting,
        wfNode.unitsCycles,
        wfNode.movingTime,
        wfNode.overlapUnits,
        wfNode.bPartnerId,
        wfNode.queuingTime,
        wfNode.resourceId,
        wfNode.setupTime,
        wfNode.validFrom,
        wfNode.`yield`,
        wfNode.viewId,
        wfNode.browseId,
        wfNode.uuid)


   private def as(wfNode: context.workflow.infrastructure.service.grpc.wf_node.WfNode) = 
      context.workflow.domain.model.WfNode(
        wfNode.wfNodeId,
        wfNode.name,
        wfNode.description,
        wfNode.workflowId,
        wfNode.clientId,
        wfNode.orgId,
        wfNode.isActive,
        wfNode.created,
        wfNode.createdBy,
        wfNode.updated,
        wfNode.updatedBy,
        wfNode.help,
        wfNode.action,
        wfNode.windowId,
        wfNode.workflowId,
        wfNode.taskId,
        wfNode.processId,
        wfNode.formId,
        wfNode.isCentrallyMaintained,
        wfNode.yPosition,
        wfNode.entityType,
        wfNode.xPosition,
        wfNode.limit,
        wfNode.startMode,
        wfNode.wfResponsibleId,
        wfNode.duration,
        wfNode.subflowExecution,
        wfNode.cost,
        wfNode.waitingTime,
        wfNode.workingTime,
        wfNode.wfBlockId,
        wfNode.priority,
        wfNode.finishMode,
        wfNode.joinElement,
        wfNode.splitElement,
        wfNode.imageId,
        wfNode.columnId,
        wfNode.waitTime,
        wfNode.attributeValue,
        wfNode.attributeName,
        wfNode.docAction,
        wfNode.value,
        wfNode.dynPriorityUnit,
        wfNode.dynPriorityChange,
        wfNode.eMailRecipient,
        wfNode.eMail,
        wfNode.mailTextId,
        wfNode.validTo,
        wfNode.isMilestone,
        wfNode.isSubcontracting,
        wfNode.unitsCycles,
        wfNode.movingTime,
        wfNode.overlapUnits,
        wfNode.bPartnerId,
        wfNode.queuingTime,
        wfNode.resourceId,
        wfNode.setupTime,
        wfNode.validFrom,
        wfNode.`yield`,
        wfNode.viewId,
        wfNode.browseId,
        wfNode.uuid)

 }
}