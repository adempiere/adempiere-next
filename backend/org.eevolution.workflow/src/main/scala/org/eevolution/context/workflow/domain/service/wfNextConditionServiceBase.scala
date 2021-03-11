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
import org.eevolution.context.workflow.domain.wfNextConditionRepositoryApi.WfNextConditionRepository
import org.eevolution.context.workflow.domain.wfNextConditionServiceApi.WfNextConditionService
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import zio.RIO

/*
* WfNextCondition Service Base Trait with the implementation for WfNextCondition Domain Service Implementation
*
* Is a contract to define the Service Base Implementation for WfNextCondition Domain Service
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfNextConditionServiceBase {

  abstract class Service(wfNextConditionRepository: WfNextConditionRepository.Service)
    extends WfNextConditionService.Service {

      override def getByWfNextConditionId(
					wfNextConditionId: Id
			): RIO[WfNextConditionRepository, Option[WfNextCondition]] = 
				wfNextConditionRepository.getByWfNextConditionId(wfNextConditionId)

      override def getByWfNodeNextId(
					wfNodeNextId: TableDirect
			): RIO[WfNextConditionRepository, List[WfNextCondition]] = 
				wfNextConditionRepository.getByWfNodeNextId(wfNodeNextId)

      override def getByValue(
					value: String
			): RIO[WfNextConditionRepository, Option[WfNextCondition]] = 
				wfNextConditionRepository.getByValue(value)

    override def getAll: RIO[WfNextConditionRepository, List[WfNextCondition]] = 
      wfNextConditionRepository.getAll
    
      override def getAllByClientId(tenantId: TableDirect): RIO[WfNextConditionRepository, List[WfNextCondition]] = 
      wfNextConditionRepository.getAllByClientId(tenantId)    
      
      override def save(wfNextCondition: WfNextCondition): RIO[WfNextConditionRepository, WfNextCondition] = 
      wfNextConditionRepository.save(wfNextCondition)   
      
  }
}
