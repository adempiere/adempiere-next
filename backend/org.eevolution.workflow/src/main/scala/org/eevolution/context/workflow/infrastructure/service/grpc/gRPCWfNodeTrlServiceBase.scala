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
import org.eevolution.context.workflow.domain.wfNodeTrlRepositoryApi.WfNodeTrlRepository
import org.eevolution.context.workflow.domain.wfNodeTrlServiceApi
import org.eevolution.context.workflow.domain.wfNodeTrlServiceApi.WfNodeTrlService
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_node_trl._
import org.eevolution.context.workflow.infrastructure.service.grpc.wf_node_trl.ZioWfNodeTrl.RWfNodeTrlService
import zio.ZIO
import zio.stream.ZStream

/*
* gRPC WfNodeTrl Service Base Trait with the implementation for gRPC WfNodeTrl Service Implementation
*
* Is a contract to define the Service Base Implementation for gRPC WfNodeTrl Service
* This class should not be modified since it is generated from ZIO gRPC plugin and the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait gRPCWfNodeTrlServiceBase {

  class Service[R] extends RWfNodeTrlService[WfNodeTrlService with WfNodeTrlRepository] {

    
    override def getWfNodeTrlAll(request: WfNodeTrlAllRequest): ZStream[
      WfNodeTrlService with WfNodeTrlRepository with Any,
      Status,
      WfNodeTrlStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeTrlServiceApi.getAll
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeTrlList =>
                WfNodeTrlStreamResponse.of(
                  wfNodeTrlList.map(wfNodeTrl => ~>(wfNodeTrl))
                )
            )
        )
    
    override def getWfNodeTrlAllByClientId(
        request: WfNodeTrlAllByClientIdRequest
    ): ZStream[
      WfNodeTrlService with WfNodeTrlRepository with Any,
      Status,
      WfNodeTrlStreamResponse
    ] =
      ZStream
        .fromEffect(
          wfNodeTrlServiceApi
            .getAllByClientId(request.getTenantId.value.intValue)
            .bimap(
              _ => Status.NOT_FOUND,
              wfNodeTrlList =>
                WfNodeTrlStreamResponse.of(
                  wfNodeTrlList.map(wfNodeTrl => ~>(wfNodeTrl))
                )
            )
        )
    

override def save(request: WfNodeTrlSaveRequest): ZIO[
      WfNodeTrlService with WfNodeTrlRepository with Any,
      Status,
      WfNodeTrlResponse
    ] = {
      val wfNodeTrl = request.getWfNodeTrl
      wfNodeTrlServiceApi
        .save(as(wfNodeTrl))
        .bimap(
          _ => Status.NOT_FOUND,
          wfNodeTrl => WfNodeTrlResponse(~>?(wfNodeTrl))
        )
    }
    
    private def ~>?(
        wfNodeTrl: context.workflow.domain.model.WfNodeTrl
    ): Option[WfNodeTrl] = {
      Option(~>(wfNodeTrl))
    }

    private def ~>(
        wfNodeTrl: context.workflow.domain.model.WfNodeTrl
    ): WfNodeTrl = 
      WfNodeTrl(
        wfNodeTrl.wfNodeId,
        wfNodeTrl.language,
        wfNodeTrl.name,
        wfNodeTrl.description,
        wfNodeTrl.isTranslated,
        wfNodeTrl.isActive,
        wfNodeTrl.created,
        wfNodeTrl.createdBy,
        wfNodeTrl.updated,
        wfNodeTrl.updatedBy,
        wfNodeTrl.clientId,
        wfNodeTrl.orgId,
        wfNodeTrl.help,
        wfNodeTrl.uuid)


   private def as(wfNodeTrl: context.workflow.infrastructure.service.grpc.wf_node_trl.WfNodeTrl) = 
      context.workflow.domain.model.WfNodeTrl(
        wfNodeTrl.wfNodeId,
        wfNodeTrl.language,
        wfNodeTrl.name,
        wfNodeTrl.description,
        wfNodeTrl.isTranslated,
        wfNodeTrl.isActive,
        wfNodeTrl.created,
        wfNodeTrl.createdBy,
        wfNodeTrl.updated,
        wfNodeTrl.updatedBy,
        wfNodeTrl.clientId,
        wfNodeTrl.orgId,
        wfNodeTrl.help,
        wfNodeTrl.uuid)

 }
}