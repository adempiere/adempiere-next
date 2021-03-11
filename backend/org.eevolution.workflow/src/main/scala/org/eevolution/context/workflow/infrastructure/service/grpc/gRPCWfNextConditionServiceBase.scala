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
import org.eevolution.context.workflow.domain.wfNextConditionRepositoryApi.WfNextConditionRepository
import org.eevolution.context.workflow.domain.wfNextConditionServiceApi
import org.eevolution.context.workflow.domain.wfNextConditionServiceApi.WfNextConditionService
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_next_condition._
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_next_condition.ZioWfNextCondition.RWfNextConditionService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WfNextCondition Service Base Trait with the implementation for gRPC WfNextCondition Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WfNextCondition Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWfNextConditionServiceBase {

  class Service[R] extends RWfNextConditionService[WfNextConditionService with WfNextConditionRepository] {


    override def getWfNextConditionByWfNextConditionId(request: WfNextConditionByWfNextConditionIdRequest): ZIO[
      WfNextConditionService with WfNextConditionRepository with Any,
      Status,
      WfNextConditionResponse
    ] =
      wfNextConditionServiceApi
        .getByWfNextConditionId(request.wfNextConditionId)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfNextCondition => {
            val wfNextCondition = maybeWfNextCondition.get
            WfNextConditionResponse(
              Option(
                ~>(wfNextCondition)
              )
            )
          }
        )



override def getWfNextConditionByWfNodeNextId(
        request: WfNextConditionByWfNodeNextIdRequest
    ): ZStream[
      WfNextConditionService with WfNextConditionRepository with Any,
      Status,
      WfNextConditionStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNextConditionServiceApi
            .getByWfNodeNextId(request.wfNodeNextId)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNextConditionList =>
                WfNextConditionStreamResponse(
                  wfNextConditionList.map(wfNextCondition => ~>(wfNextCondition))
                )
            )
        )



    override def getWfNextConditionByValue(request: WfNextConditionByValueRequest): ZIO[
      WfNextConditionService with WfNextConditionRepository with Any,
      Status,
      WfNextConditionResponse
    ] =
      wfNextConditionServiceApi
        .getByValue(request.value)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfNextCondition => {
            val wfNextCondition = maybeWfNextCondition.get
            WfNextConditionResponse(
              Option(
                ~>(wfNextCondition)
              )
            )
          }
        )


    
    override def getWfNextConditionAll(request: WfNextConditionAllRequest): ZStream[
      WfNextConditionService with WfNextConditionRepository with Any,
      Status,
      WfNextConditionStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNextConditionServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              wfNextConditionList =>
                WfNextConditionStreamResponse.of(
                  wfNextConditionList.map(wfNextCondition => ~>(wfNextCondition))
                )
            )
        )
    
    override def getWfNextConditionAllByClientId(
        request: WfNextConditionAllByClientIdRequest
    ): ZStream[
      WfNextConditionService with WfNextConditionRepository with Any,
      Status,
      WfNextConditionStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNextConditionServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNextConditionList =>
                WfNextConditionStreamResponse.of(
                  wfNextConditionList.map(wfNextCondition => ~>(wfNextCondition))
                )
            )
        )
    

override def save(request: WfNextConditionSaveRequest): ZIO[
      WfNextConditionService with WfNextConditionRepository with Any,
      Status,
      WfNextConditionResponse
    ] = {
      val wfNextCondition = request.getWfNextCondition
      wfNextConditionServiceApi
        .save(as(wfNextCondition))
        .bimap(
          _ => Status.NOT_FOUND,
          wfNextCondition => WfNextConditionResponse(~>?(wfNextCondition))
        )
    }
    
    private def ~>?(
        wfNextCondition: context.workflow.domain.model.WfNextCondition
    ): Option[WfNextCondition] = {
      Option(~>(wfNextCondition))
    }

    private def ~>(
        wfNextCondition: context.workflow.domain.model.WfNextCondition
    ): WfNextCondition = 
      WfNextCondition(
        wfNextCondition.createdBy,
        wfNextCondition.wfNextConditionId,
        wfNextCondition.andOr,
        wfNextCondition.created,
        wfNextCondition.operation,
        wfNextCondition.orgId,
        wfNextCondition.clientId,
        wfNextCondition.wfNodeNextId,
        wfNextCondition.seqNo,
        wfNextCondition.value,
        wfNextCondition.updatedBy,
        wfNextCondition.isActive,
        wfNextCondition.value2,
        wfNextCondition.entityType,
        wfNextCondition.updated,
        wfNextCondition.columnId,
        wfNextCondition.uuid)


   private def as(wfNextCondition: context.workflow.infrastructure.service.grpc.wf_next_condition.WfNextCondition) = 
      context.workflow.domain.model.WfNextCondition(
        wfNextCondition.createdBy,
        wfNextCondition.wfNextConditionId,
        wfNextCondition.andOr,
        wfNextCondition.created,
        wfNextCondition.operation,
        wfNextCondition.orgId,
        wfNextCondition.clientId,
        wfNextCondition.wfNodeNextId,
        wfNextCondition.seqNo,
        wfNextCondition.value,
        wfNextCondition.updatedBy,
        wfNextCondition.isActive,
        wfNextCondition.value2,
        wfNextCondition.entityType,
        wfNextCondition.updated,
        wfNextCondition.columnId,
        wfNextCondition.uuid)

 }
}