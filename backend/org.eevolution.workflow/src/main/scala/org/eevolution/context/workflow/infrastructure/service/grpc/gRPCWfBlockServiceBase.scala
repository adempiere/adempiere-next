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
import org.eevolution.context.workflow.domain.wfBlockRepositoryApi.WfBlockRepository
import org.eevolution.context.workflow.domain.wfBlockServiceApi
import org.eevolution.context.workflow.domain.wfBlockServiceApi.WfBlockService
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_block._
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_block.ZioWfBlock.RWfBlockService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WfBlock Service Base Trait with the implementation for gRPC WfBlock Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WfBlock Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWfBlockServiceBase {

  class Service[R] extends RWfBlockService[WfBlockService with WfBlockRepository] {


override def getWfBlockByDescription(
        request: WfBlockByDescriptionRequest
    ): ZStream[
      WfBlockService with WfBlockRepository with Any,
      Status,
      WfBlockStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfBlockServiceApi
            .getByDescription(request.description)
            .bimap(
              _ => Status.NOT_FOUND,
              wfBlockList =>
                WfBlockStreamResponse(
                  wfBlockList.map(wfBlock => ~>(wfBlock))
                )
            )
        )



override def getWfBlockByWorkflowId(
        request: WfBlockByWorkflowIdRequest
    ): ZStream[
      WfBlockService with WfBlockRepository with Any,
      Status,
      WfBlockStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfBlockServiceApi
            .getByWorkflowId(request.workflowId)
            .bimap(
              _ => Status.NOT_FOUND,
              wfBlockList =>
                WfBlockStreamResponse(
                  wfBlockList.map(wfBlock => ~>(wfBlock))
                )
            )
        )



    override def getWfBlockByName(request: WfBlockByNameRequest): ZIO[
      WfBlockService with WfBlockRepository with Any,
      Status,
      WfBlockResponse
    ] =
      wfBlockServiceApi
        .getByName(request.name)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfBlock => {
            val wfBlock = maybeWfBlock.get
            WfBlockResponse(
              Option(
                ~>(wfBlock)
              )
            )
          }
        )



    override def getWfBlockByWfBlockId(request: WfBlockByWfBlockIdRequest): ZIO[
      WfBlockService with WfBlockRepository with Any,
      Status,
      WfBlockResponse
    ] =
      wfBlockServiceApi
        .getByWfBlockId(request.wfBlockId)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfBlock => {
            val wfBlock = maybeWfBlock.get
            WfBlockResponse(
              Option(
                ~>(wfBlock)
              )
            )
          }
        )


    
    override def getWfBlockAll(request: WfBlockAllRequest): ZStream[
      WfBlockService with WfBlockRepository with Any,
      Status,
      WfBlockStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfBlockServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              wfBlockList =>
                WfBlockStreamResponse.of(
                  wfBlockList.map(wfBlock => ~>(wfBlock))
                )
            )
        )
    
    override def getWfBlockAllByClientId(
        request: WfBlockAllByClientIdRequest
    ): ZStream[
      WfBlockService with WfBlockRepository with Any,
      Status,
      WfBlockStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfBlockServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              wfBlockList =>
                WfBlockStreamResponse.of(
                  wfBlockList.map(wfBlock => ~>(wfBlock))
                )
            )
        )
    

override def save(request: WfBlockSaveRequest): ZIO[
      WfBlockService with WfBlockRepository with Any,
      Status,
      WfBlockResponse
    ] = {
      val wfBlock = request.getWfBlock
      wfBlockServiceApi
        .save(as(wfBlock))
        .bimap(
          _ => Status.NOT_FOUND,
          wfBlock => WfBlockResponse(~>?(wfBlock))
        )
    }
    
    private def ~>?(
        wfBlock: context.workflow.domain.model.WfBlock
    ): Option[WfBlock] = {
      Option(~>(wfBlock))
    }

    private def ~>(
        wfBlock: context.workflow.domain.model.WfBlock
    ): WfBlock = 
      WfBlock(
        wfBlock.description,
        wfBlock.clientId,
        wfBlock.workflowId,
        wfBlock.updated,
        wfBlock.createdBy,
        wfBlock.isActive,
        wfBlock.name,
        wfBlock.created,
        wfBlock.orgId,
        wfBlock.wfBlockId,
        wfBlock.updatedBy,
        wfBlock.uuid)


   private def as(wfBlock: context.workflow.infrastructure.service.grpc.wf_block.WfBlock) = 
      context.workflow.domain.model.WfBlock(
        wfBlock.description,
        wfBlock.clientId,
        wfBlock.workflowId,
        wfBlock.updated,
        wfBlock.createdBy,
        wfBlock.isActive,
        wfBlock.name,
        wfBlock.created,
        wfBlock.orgId,
        wfBlock.wfBlockId,
        wfBlock.updatedBy,
        wfBlock.uuid)

 }
}