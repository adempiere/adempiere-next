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
import org.eevolution.context.workflow.domain.wfActivityResultRepositoryApi.WfActivityResultRepository
import org.eevolution.context.workflow.domain.wfActivityResultServiceApi
import org.eevolution.context.workflow.domain.wfActivityResultServiceApi.WfActivityResultService
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_activity_result._
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_activity_result.ZioWfActivityResult.RWfActivityResultService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WfActivityResult Service Base Trait with the implementation for gRPC WfActivityResult Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WfActivityResult Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWfActivityResultServiceBase {

  class Service[R] extends RWfActivityResultService[WfActivityResultService with WfActivityResultRepository] {


override def getWfActivityResultByWfActivityId(
        request: WfActivityResultByWfActivityIdRequest
    ): ZStream[
      WfActivityResultService with WfActivityResultRepository with Any,
      Status,
      WfActivityResultStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfActivityResultServiceApi
            .getByWfActivityId(request.wfActivityId)
            .bimap(
              _ => Status.NOT_FOUND,
              wfActivityResultList =>
                WfActivityResultStreamResponse(
                  wfActivityResultList.map(wfActivityResult => ~>(wfActivityResult))
                )
            )
        )



override def getWfActivityResultByAttributeName(
        request: WfActivityResultByAttributeNameRequest
    ): ZStream[
      WfActivityResultService with WfActivityResultRepository with Any,
      Status,
      WfActivityResultStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfActivityResultServiceApi
            .getByAttributeName(request.attributeName)
            .bimap(
              _ => Status.NOT_FOUND,
              wfActivityResultList =>
                WfActivityResultStreamResponse(
                  wfActivityResultList.map(wfActivityResult => ~>(wfActivityResult))
                )
            )
        )



    override def getWfActivityResultByWfActivityResultId(request: WfActivityResultByWfActivityResultIdRequest): ZIO[
      WfActivityResultService with WfActivityResultRepository with Any,
      Status,
      WfActivityResultResponse
    ] =
      wfActivityResultServiceApi
        .getByWfActivityResultId(request.wfActivityResultId)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfActivityResult => {
            val wfActivityResult = maybeWfActivityResult.get
            WfActivityResultResponse(
              Option(
                ~>(wfActivityResult)
              )
            )
          }
        )



override def getWfActivityResultByDescription(
        request: WfActivityResultByDescriptionRequest
    ): ZStream[
      WfActivityResultService with WfActivityResultRepository with Any,
      Status,
      WfActivityResultStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfActivityResultServiceApi
            .getByDescription(request.description)
            .bimap(
              _ => Status.NOT_FOUND,
              wfActivityResultList =>
                WfActivityResultStreamResponse(
                  wfActivityResultList.map(wfActivityResult => ~>(wfActivityResult))
                )
            )
        )


    
    override def getWfActivityResultAll(request: WfActivityResultAllRequest): ZStream[
      WfActivityResultService with WfActivityResultRepository with Any,
      Status,
      WfActivityResultStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfActivityResultServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              wfActivityResultList =>
                WfActivityResultStreamResponse.of(
                  wfActivityResultList.map(wfActivityResult => ~>(wfActivityResult))
                )
            )
        )
    
    override def getWfActivityResultAllByClientId(
        request: WfActivityResultAllByClientIdRequest
    ): ZStream[
      WfActivityResultService with WfActivityResultRepository with Any,
      Status,
      WfActivityResultStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfActivityResultServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              wfActivityResultList =>
                WfActivityResultStreamResponse.of(
                  wfActivityResultList.map(wfActivityResult => ~>(wfActivityResult))
                )
            )
        )
    

override def save(request: WfActivityResultSaveRequest): ZIO[
      WfActivityResultService with WfActivityResultRepository with Any,
      Status,
      WfActivityResultResponse
    ] = {
      val wfActivityResult = request.getWfActivityResult
      wfActivityResultServiceApi
        .save(as(wfActivityResult))
        .bimap(
          _ => Status.NOT_FOUND,
          wfActivityResult => WfActivityResultResponse(~>?(wfActivityResult))
        )
    }
    
    private def ~>?(
        wfActivityResult: context.workflow.domain.model.WfActivityResult
    ): Option[WfActivityResult] = {
      Option(~>(wfActivityResult))
    }

    private def ~>(
        wfActivityResult: context.workflow.domain.model.WfActivityResult
    ): WfActivityResult = 
      WfActivityResult(
        wfActivityResult.clientId,
        wfActivityResult.orgId,
        wfActivityResult.wfActivityId,
        wfActivityResult.attributeName,
        wfActivityResult.createdBy,
        wfActivityResult.updatedBy,
        wfActivityResult.updated,
        wfActivityResult.isActive,
        wfActivityResult.attributeValue,
        wfActivityResult.created,
        wfActivityResult.wfActivityResultId,
        wfActivityResult.description,
        wfActivityResult.help,
        wfActivityResult.uuid)


   private def as(wfActivityResult: context.workflow.infrastructure.service.grpc.wf_activity_result.WfActivityResult) = 
      context.workflow.domain.model.WfActivityResult(
        wfActivityResult.clientId,
        wfActivityResult.orgId,
        wfActivityResult.wfActivityId,
        wfActivityResult.attributeName,
        wfActivityResult.createdBy,
        wfActivityResult.updatedBy,
        wfActivityResult.updated,
        wfActivityResult.isActive,
        wfActivityResult.attributeValue,
        wfActivityResult.created,
        wfActivityResult.wfActivityResultId,
        wfActivityResult.description,
        wfActivityResult.help,
        wfActivityResult.uuid)

 }
}