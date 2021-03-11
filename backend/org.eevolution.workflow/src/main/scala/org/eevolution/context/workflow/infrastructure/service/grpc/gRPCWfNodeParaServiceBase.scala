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
import org.eevolution.context.workflow.domain.wfNodeParaRepositoryApi.WfNodeParaRepository
import org.eevolution.context.workflow.domain.wfNodeParaServiceApi
import org.eevolution.context.workflow.domain.wfNodeParaServiceApi.WfNodeParaService
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_node_para._
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_node_para.ZioWfNodePara.RWfNodeParaService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WfNodePara Service Base Trait with the implementation for gRPC WfNodePara Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WfNodePara Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWfNodeParaServiceBase {

  class Service[R] extends RWfNodeParaService[WfNodeParaService with WfNodeParaRepository] {


override def getWfNodeParaByWfNodeId(
        request: WfNodeParaByWfNodeIdRequest
    ): ZStream[
      WfNodeParaService with WfNodeParaRepository with Any,
      Status,
      WfNodeParaStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeParaServiceApi
            .getByWfNodeId(request.wfNodeId)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeParaList =>
                WfNodeParaStreamResponse(
                  wfNodeParaList.map(wfNodePara => ~>(wfNodePara))
                )
            )
        )



override def getWfNodeParaByDescription(
        request: WfNodeParaByDescriptionRequest
    ): ZStream[
      WfNodeParaService with WfNodeParaRepository with Any,
      Status,
      WfNodeParaStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeParaServiceApi
            .getByDescription(request.description)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeParaList =>
                WfNodeParaStreamResponse(
                  wfNodeParaList.map(wfNodePara => ~>(wfNodePara))
                )
            )
        )



override def getWfNodeParaByAttributeName(
        request: WfNodeParaByAttributeNameRequest
    ): ZStream[
      WfNodeParaService with WfNodeParaRepository with Any,
      Status,
      WfNodeParaStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeParaServiceApi
            .getByAttributeName(request.attributeName)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeParaList =>
                WfNodeParaStreamResponse(
                  wfNodeParaList.map(wfNodePara => ~>(wfNodePara))
                )
            )
        )



    override def getWfNodeParaByWfNodeParaId(request: WfNodeParaByWfNodeParaIdRequest): ZIO[
      WfNodeParaService with WfNodeParaRepository with Any,
      Status,
      WfNodeParaResponse
    ] =
      wfNodeParaServiceApi
        .getByWfNodeParaId(request.wfNodeParaId)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfNodePara => {
            val wfNodePara = maybeWfNodePara.get
            WfNodeParaResponse(
              Option(
                ~>(wfNodePara)
              )
            )
          }
        )


    
    override def getWfNodeParaAll(request: WfNodeParaAllRequest): ZStream[
      WfNodeParaService with WfNodeParaRepository with Any,
      Status,
      WfNodeParaStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeParaServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeParaList =>
                WfNodeParaStreamResponse.of(
                  wfNodeParaList.map(wfNodePara => ~>(wfNodePara))
                )
            )
        )
    
    override def getWfNodeParaAllByClientId(
        request: WfNodeParaAllByClientIdRequest
    ): ZStream[
      WfNodeParaService with WfNodeParaRepository with Any,
      Status,
      WfNodeParaStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeParaServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeParaList =>
                WfNodeParaStreamResponse.of(
                  wfNodeParaList.map(wfNodePara => ~>(wfNodePara))
                )
            )
        )
    

override def save(request: WfNodeParaSaveRequest): ZIO[
      WfNodeParaService with WfNodeParaRepository with Any,
      Status,
      WfNodeParaResponse
    ] = {
      val wfNodePara = request.getWfNodePara
      wfNodeParaServiceApi
        .save(as(wfNodePara))
        .bimap(
          _ => Status.NOT_FOUND,
          wfNodePara => WfNodeParaResponse(~>?(wfNodePara))
        )
    }
    
    private def ~>?(
        wfNodePara: context.workflow.domain.model.WfNodePara
    ): Option[WfNodePara] = {
      Option(~>(wfNodePara))
    }

    private def ~>(
        wfNodePara: context.workflow.domain.model.WfNodePara
    ): WfNodePara = 
      WfNodePara(
        wfNodePara.wfNodeId,
        wfNodePara.createdBy,
        wfNodePara.created,
        wfNodePara.description,
        wfNodePara.attributeName,
        wfNodePara.wfNodeParaId,
        wfNodePara.orgId,
        wfNodePara.processParaId,
        wfNodePara.clientId,
        wfNodePara.updatedBy,
        wfNodePara.isActive,
        wfNodePara.updated,
        wfNodePara.attributeValue,
        wfNodePara.entityType,
        wfNodePara.uuid)


   private def as(wfNodePara: context.workflow.infrastructure.service.grpc.wf_node_para.WfNodePara) = 
      context.workflow.domain.model.WfNodePara(
        wfNodePara.wfNodeId,
        wfNodePara.createdBy,
        wfNodePara.created,
        wfNodePara.description,
        wfNodePara.attributeName,
        wfNodePara.wfNodeParaId,
        wfNodePara.orgId,
        wfNodePara.processParaId,
        wfNodePara.clientId,
        wfNodePara.updatedBy,
        wfNodePara.isActive,
        wfNodePara.updated,
        wfNodePara.attributeValue,
        wfNodePara.entityType,
        wfNodePara.uuid)

 }
}