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
import org.eevolution.context.workflow.domain.wfProcessDataRepositoryApi.WfProcessDataRepository
import org.eevolution.context.workflow.domain.wfProcessDataServiceApi.WfProcessDataService
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import zio.RIO

/*
* WfProcessData Service Base Trait with the implementation for WfProcessData Domain Service Implementation
*
* Is a contract to define the Service Base Implementation for WfProcessData Domain Service
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfProcessDataServiceBase {

  abstract class Service(wfProcessDataRepository: WfProcessDataRepository.Service)
    extends WfProcessDataService.Service {

      override def getByWfProcessId(
					wfProcessId: TableDirect
			): RIO[WfProcessDataRepository, List[WfProcessData]] = 
				wfProcessDataRepository.getByWfProcessId(wfProcessId)

      override def getByAttributeName(
					attributeName: String
			): RIO[WfProcessDataRepository, List[WfProcessData]] = 
				wfProcessDataRepository.getByAttributeName(attributeName)

      override def getByWfProcessDataId(
					wfProcessDataId: Id
			): RIO[WfProcessDataRepository, Option[WfProcessData]] = 
				wfProcessDataRepository.getByWfProcessDataId(wfProcessDataId)

    override def getAll: RIO[WfProcessDataRepository, List[WfProcessData]] = 
      wfProcessDataRepository.getAll
    
      override def getAllByClientId(tenantId: TableDirect): RIO[WfProcessDataRepository, List[WfProcessData]] = 
      wfProcessDataRepository.getAllByClientId(tenantId)    
      
      override def save(wfProcessData: WfProcessData): RIO[WfProcessDataRepository, WfProcessData] = 
      wfProcessDataRepository.save(wfProcessData)   
      
  }
}
