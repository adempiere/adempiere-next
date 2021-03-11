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

package org.eevolution.context.workflow.domain.service

import org.eevolution.context.workflow.domain.model._
import org.eevolution.context.workflow.domain.wfNodeParaRepositoryApi.WfNodeParaRepository
import org.eevolution.context.workflow.domain.wfNodeParaServiceApi.WfNodeParaService
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import zio.RIO

/*
* WfNodePara Service Base Trait with the implementation for WfNodePara Domain Service Implementation
*
* Is a contract to define the Service Base Implementation for WfNodePara Domain Service
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfNodeParaServiceBase {

  abstract class Service(wfNodeParaRepository: WfNodeParaRepository.Service)
    extends WfNodeParaService.Service {

      override def getByWfNodeId(
					wfNodeId: TableDirect
			): RIO[WfNodeParaRepository, List[WfNodePara]] = 
				wfNodeParaRepository.getByWfNodeId(wfNodeId)

      override def getByDescription(
					description: Option[String]
			): RIO[WfNodeParaRepository, List[WfNodePara]] = 
				wfNodeParaRepository.getByDescription(description)

      override def getByAttributeName(
					attributeName: Option[String]
			): RIO[WfNodeParaRepository, List[WfNodePara]] = 
				wfNodeParaRepository.getByAttributeName(attributeName)

      override def getByWfNodeParaId(
					wfNodeParaId: Id
			): RIO[WfNodeParaRepository, Option[WfNodePara]] = 
				wfNodeParaRepository.getByWfNodeParaId(wfNodeParaId)

    override def getAll: RIO[WfNodeParaRepository, List[WfNodePara]] = 
      wfNodeParaRepository.getAll
    
      override def getAllByClientId(tenantId: TableDirect): RIO[WfNodeParaRepository, List[WfNodePara]] = 
      wfNodeParaRepository.getAllByClientId(tenantId)    
      
      override def save(wfNodePara: WfNodePara): RIO[WfNodeParaRepository, WfNodePara] = 
      wfNodeParaRepository.save(wfNodePara)   
      
  }
}
