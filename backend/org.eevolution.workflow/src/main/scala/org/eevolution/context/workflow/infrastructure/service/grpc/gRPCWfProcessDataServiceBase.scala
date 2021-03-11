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
import org.eevolution.context.workflow.domain.wfProcessDataRepositoryApi.WfProcessDataRepository
import org.eevolution.context.workflow.domain.wfProcessDataServiceApi
import org.eevolution.context.workflow.domain.wfProcessDataServiceApi.WfProcessDataService
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_process_data._
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_process_data.ZioWfProcessData.RWfProcessDataService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WfProcessData Service Base Trait with the implementation for gRPC WfProcessData Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WfProcessData Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWfProcessDataServiceBase {

  class Service[R] extends RWfProcessDataService[WfProcessDataService with WfProcessDataRepository] {


override def getWfProcessDataByWfProcessId(
        request: WfProcessDataByWfProcessIdRequest
    ): ZStream[
      WfProcessDataService with WfProcessDataRepository with Any,
      Status,
      WfProcessDataStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfProcessDataServiceApi
            .getByWfProcessId(request.wfProcessId)
            .bimap(
              _ => Status.NOT_FOUND,
              wfProcessDataList =>
                WfProcessDataStreamResponse(
                  wfProcessDataList.map(wfProcessData => ~>(wfProcessData))
                )
            )
        )



override def getWfProcessDataByAttributeName(
        request: WfProcessDataByAttributeNameRequest
    ): ZStream[
      WfProcessDataService with WfProcessDataRepository with Any,
      Status,
      WfProcessDataStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfProcessDataServiceApi
            .getByAttributeName(request.attributeName)
            .bimap(
              _ => Status.NOT_FOUND,
              wfProcessDataList =>
                WfProcessDataStreamResponse(
                  wfProcessDataList.map(wfProcessData => ~>(wfProcessData))
                )
            )
        )



    override def getWfProcessDataByWfProcessDataId(request: WfProcessDataByWfProcessDataIdRequest): ZIO[
      WfProcessDataService with WfProcessDataRepository with Any,
      Status,
      WfProcessDataResponse
    ] =
      wfProcessDataServiceApi
        .getByWfProcessDataId(request.wfProcessDataId)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfProcessData => {
            val wfProcessData = maybeWfProcessData.get
            WfProcessDataResponse(
              Option(
                ~>(wfProcessData)
              )
            )
          }
        )


    
    override def getWfProcessDataAll(request: WfProcessDataAllRequest): ZStream[
      WfProcessDataService with WfProcessDataRepository with Any,
      Status,
      WfProcessDataStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfProcessDataServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              wfProcessDataList =>
                WfProcessDataStreamResponse.of(
                  wfProcessDataList.map(wfProcessData => ~>(wfProcessData))
                )
            )
        )
    
    override def getWfProcessDataAllByClientId(
        request: WfProcessDataAllByClientIdRequest
    ): ZStream[
      WfProcessDataService with WfProcessDataRepository with Any,
      Status,
      WfProcessDataStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfProcessDataServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              wfProcessDataList =>
                WfProcessDataStreamResponse.of(
                  wfProcessDataList.map(wfProcessData => ~>(wfProcessData))
                )
            )
        )
    

override def save(request: WfProcessDataSaveRequest): ZIO[
      WfProcessDataService with WfProcessDataRepository with Any,
      Status,
      WfProcessDataResponse
    ] = {
      val wfProcessData = request.getWfProcessData
      wfProcessDataServiceApi
        .save(as(wfProcessData))
        .bimap(
          _ => Status.NOT_FOUND,
          wfProcessData => WfProcessDataResponse(~>?(wfProcessData))
        )
    }
    
    private def ~>?(
        wfProcessData: context.workflow.domain.model.WfProcessData
    ): Option[WfProcessData] = {
      Option(~>(wfProcessData))
    }

    private def ~>(
        wfProcessData: context.workflow.domain.model.WfProcessData
    ): WfProcessData = 
      WfProcessData(
        wfProcessData.wfProcessId,
        wfProcessData.attributeName,
        wfProcessData.isActive,
        wfProcessData.createdBy,
        wfProcessData.updatedBy,
        wfProcessData.wfProcessDataId,
        wfProcessData.clientId,
        wfProcessData.orgId,
        wfProcessData.updated,
        wfProcessData.attributeValue,
        wfProcessData.created,
        wfProcessData.uuid)


   private def as(wfProcessData: context.workflow.infrastructure.service.grpc.wf_process_data.WfProcessData) = 
      context.workflow.domain.model.WfProcessData(
        wfProcessData.wfProcessId,
        wfProcessData.attributeName,
        wfProcessData.isActive,
        wfProcessData.createdBy,
        wfProcessData.updatedBy,
        wfProcessData.wfProcessDataId,
        wfProcessData.clientId,
        wfProcessData.orgId,
        wfProcessData.updated,
        wfProcessData.attributeValue,
        wfProcessData.created,
        wfProcessData.uuid)

 }
}