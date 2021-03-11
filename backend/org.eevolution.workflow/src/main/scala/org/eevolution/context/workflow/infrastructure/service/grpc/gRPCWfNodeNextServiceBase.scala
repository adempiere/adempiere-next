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
import org.eevolution.context.workflow.domain.wfNodeNextRepositoryApi.WfNodeNextRepository
import org.eevolution.context.workflow.domain.wfNodeNextServiceApi
import org.eevolution.context.workflow.domain.wfNodeNextServiceApi.WfNodeNextService
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_node_next._
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_node_next.ZioWfNodeNext.RWfNodeNextService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WfNodeNext Service Base Trait with the implementation for gRPC WfNodeNext Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WfNodeNext Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWfNodeNextServiceBase {

  class Service[R] extends RWfNodeNextService[WfNodeNextService with WfNodeNextRepository] {


override def getWfNodeNextByWfNodeId(
        request: WfNodeNextByWfNodeIdRequest
    ): ZStream[
      WfNodeNextService with WfNodeNextRepository with Any,
      Status,
      WfNodeNextStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeNextServiceApi
            .getByWfNodeId(request.wfNodeId)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeNextList =>
                WfNodeNextStreamResponse(
                  wfNodeNextList.map(wfNodeNext => ~>(wfNodeNext))
                )
            )
        )



override def getWfNodeNextByWfNextId(
        request: WfNodeNextByWfNextIdRequest
    ): ZStream[
      WfNodeNextService with WfNodeNextRepository with Any,
      Status,
      WfNodeNextStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeNextServiceApi
            .getByWfNextId(request.wfNextId)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeNextList =>
                WfNodeNextStreamResponse(
                  wfNodeNextList.map(wfNodeNext => ~>(wfNodeNext))
                )
            )
        )



override def getWfNodeNextBySeqNo(
        request: WfNodeNextBySeqNoRequest
    ): ZStream[
      WfNodeNextService with WfNodeNextRepository with Any,
      Status,
      WfNodeNextStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeNextServiceApi
            .getBySeqNo(request.seqNo)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeNextList =>
                WfNodeNextStreamResponse(
                  wfNodeNextList.map(wfNodeNext => ~>(wfNodeNext))
                )
            )
        )



override def getWfNodeNextByDescription(
        request: WfNodeNextByDescriptionRequest
    ): ZStream[
      WfNodeNextService with WfNodeNextRepository with Any,
      Status,
      WfNodeNextStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeNextServiceApi
            .getByDescription(request.description)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeNextList =>
                WfNodeNextStreamResponse(
                  wfNodeNextList.map(wfNodeNext => ~>(wfNodeNext))
                )
            )
        )



    override def getWfNodeNextByWfNodeNextId(request: WfNodeNextByWfNodeNextIdRequest): ZIO[
      WfNodeNextService with WfNodeNextRepository with Any,
      Status,
      WfNodeNextResponse
    ] =
      wfNodeNextServiceApi
        .getByWfNodeNextId(request.wfNodeNextId)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfNodeNext => {
            val wfNodeNext = maybeWfNodeNext.get
            WfNodeNextResponse(
              Option(
                ~>(wfNodeNext)
              )
            )
          }
        )


    
    override def getWfNodeNextAll(request: WfNodeNextAllRequest): ZStream[
      WfNodeNextService with WfNodeNextRepository with Any,
      Status,
      WfNodeNextStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeNextServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeNextList =>
                WfNodeNextStreamResponse.of(
                  wfNodeNextList.map(wfNodeNext => ~>(wfNodeNext))
                )
            )
        )
    
    override def getWfNodeNextAllByClientId(
        request: WfNodeNextAllByClientIdRequest
    ): ZStream[
      WfNodeNextService with WfNodeNextRepository with Any,
      Status,
      WfNodeNextStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeNextServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeNextList =>
                WfNodeNextStreamResponse.of(
                  wfNodeNextList.map(wfNodeNext => ~>(wfNodeNext))
                )
            )
        )
    

override def save(request: WfNodeNextSaveRequest): ZIO[
      WfNodeNextService with WfNodeNextRepository with Any,
      Status,
      WfNodeNextResponse
    ] = {
      val wfNodeNext = request.getWfNodeNext
      wfNodeNextServiceApi
        .save(as(wfNodeNext))
        .bimap(
          _ => Status.NOT_FOUND,
          wfNodeNext => WfNodeNextResponse(~>?(wfNodeNext))
        )
    }
    
    private def ~>?(
        wfNodeNext: context.workflow.domain.model.WfNodeNext
    ): Option[WfNodeNext] = {
      Option(~>(wfNodeNext))
    }

    private def ~>(
        wfNodeNext: context.workflow.domain.model.WfNodeNext
    ): WfNodeNext = 
      WfNodeNext(
        wfNodeNext.wfNodeId,
        wfNodeNext.isActive,
        wfNodeNext.created,
        wfNodeNext.createdBy,
        wfNodeNext.updated,
        wfNodeNext.updatedBy,
        wfNodeNext.clientId,
        wfNodeNext.orgId,
        wfNodeNext.wfNextId,
        wfNodeNext.transitionCode,
        wfNodeNext.entityType,
        wfNodeNext.seqNo,
        wfNodeNext.description,
        wfNodeNext.wfNodeNextId,
        wfNodeNext.isStdUserWorkflow,
        wfNodeNext.uuid)


   private def as(wfNodeNext: context.workflow.infrastructure.service.grpc.wf_node_next.WfNodeNext) = 
      context.workflow.domain.model.WfNodeNext(
        wfNodeNext.wfNodeId,
        wfNodeNext.isActive,
        wfNodeNext.created,
        wfNodeNext.createdBy,
        wfNodeNext.updated,
        wfNodeNext.updatedBy,
        wfNodeNext.clientId,
        wfNodeNext.orgId,
        wfNodeNext.wfNextId,
        wfNodeNext.transitionCode,
        wfNodeNext.entityType,
        wfNodeNext.seqNo,
        wfNodeNext.description,
        wfNodeNext.wfNodeNextId,
        wfNodeNext.isStdUserWorkflow,
        wfNodeNext.uuid)

 }
}