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
import org.eevolution.context.workflow.domain.wfResponsibleRepositoryApi.WfResponsibleRepository
import org.eevolution.context.workflow.domain.wfResponsibleServiceApi
import org.eevolution.context.workflow.domain.wfResponsibleServiceApi.WfResponsibleService
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_responsible._
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_responsible.ZioWfResponsible.RWfResponsibleService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WfResponsible Service Base Trait with the implementation for gRPC WfResponsible Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WfResponsible Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWfResponsibleServiceBase {

  class Service[R] extends RWfResponsibleService[WfResponsibleService with WfResponsibleRepository] {


    override def getWfResponsibleByName(request: WfResponsibleByNameRequest): ZIO[
      WfResponsibleService with WfResponsibleRepository with Any,
      Status,
      WfResponsibleResponse
    ] =
      wfResponsibleServiceApi
        .getByName(request.name)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfResponsible => {
            val wfResponsible = maybeWfResponsible.get
            WfResponsibleResponse(
              Option(
                ~>(wfResponsible)
              )
            )
          }
        )



override def getWfResponsibleByDescription(
        request: WfResponsibleByDescriptionRequest
    ): ZStream[
      WfResponsibleService with WfResponsibleRepository with Any,
      Status,
      WfResponsibleStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfResponsibleServiceApi
            .getByDescription(request.description)
            .bimap(
              _ => Status.NOT_FOUND,
              wfResponsibleList =>
                WfResponsibleStreamResponse(
                  wfResponsibleList.map(wfResponsible => ~>(wfResponsible))
                )
            )
        )



    override def getWfResponsibleByWfResponsibleId(request: WfResponsibleByWfResponsibleIdRequest): ZIO[
      WfResponsibleService with WfResponsibleRepository with Any,
      Status,
      WfResponsibleResponse
    ] =
      wfResponsibleServiceApi
        .getByWfResponsibleId(request.wfResponsibleId)
        .bimap(
          _ => Status.NOT_FOUND,
          maybeWfResponsible => {
            val wfResponsible = maybeWfResponsible.get
            WfResponsibleResponse(
              Option(
                ~>(wfResponsible)
              )
            )
          }
        )


    
    override def getWfResponsibleAll(request: WfResponsibleAllRequest): ZStream[
      WfResponsibleService with WfResponsibleRepository with Any,
      Status,
      WfResponsibleStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfResponsibleServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              wfResponsibleList =>
                WfResponsibleStreamResponse.of(
                  wfResponsibleList.map(wfResponsible => ~>(wfResponsible))
                )
            )
        )
    
    override def getWfResponsibleAllByClientId(
        request: WfResponsibleAllByClientIdRequest
    ): ZStream[
      WfResponsibleService with WfResponsibleRepository with Any,
      Status,
      WfResponsibleStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfResponsibleServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              wfResponsibleList =>
                WfResponsibleStreamResponse.of(
                  wfResponsibleList.map(wfResponsible => ~>(wfResponsible))
                )
            )
        )
    

override def save(request: WfResponsibleSaveRequest): ZIO[
      WfResponsibleService with WfResponsibleRepository with Any,
      Status,
      WfResponsibleResponse
    ] = {
      val wfResponsible = request.getWfResponsible
      wfResponsibleServiceApi
        .save(as(wfResponsible))
        .bimap(
          _ => Status.NOT_FOUND,
          wfResponsible => WfResponsibleResponse(~>?(wfResponsible))
        )
    }
    
    private def ~>?(
        wfResponsible: context.workflow.domain.model.WfResponsible
    ): Option[WfResponsible] = {
      Option(~>(wfResponsible))
    }

    private def ~>(
        wfResponsible: context.workflow.domain.model.WfResponsible
    ): WfResponsible = 
      WfResponsible(
        wfResponsible.isActive,
        wfResponsible.responsibleType,
        wfResponsible.createdBy,
        wfResponsible.name,
        wfResponsible.created,
        wfResponsible.userId,
        wfResponsible.roleId,
        wfResponsible.description,
        wfResponsible.clientId,
        wfResponsible.updated,
        wfResponsible.orgId,
        wfResponsible.wfResponsibleId,
        wfResponsible.updatedBy,
        wfResponsible.entityType,
        wfResponsible.uuid)


   private def as(wfResponsible: context.workflow.infrastructure.service.grpc.wf_responsible.WfResponsible) = 
      context.workflow.domain.model.WfResponsible(
        wfResponsible.isActive,
        wfResponsible.responsibleType,
        wfResponsible.createdBy,
        wfResponsible.name,
        wfResponsible.created,
        wfResponsible.userId,
        wfResponsible.roleId,
        wfResponsible.description,
        wfResponsible.clientId,
        wfResponsible.updated,
        wfResponsible.orgId,
        wfResponsible.wfResponsibleId,
        wfResponsible.updatedBy,
        wfResponsible.entityType,
        wfResponsible.uuid)

 }
}