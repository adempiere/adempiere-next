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

import org.eevolution.context.workflow.domain.model.WfActivityResult
import org.eevolution.context.workflow.domain.wfActivityResultRepositoryApi.WfActivityResultRepository
import org.eevolution.context.workflow.domain.wfActivityResultServiceApi.WfActivityResultService
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import zio.blocking.Blocking
import zio.RIO

/*
* Accessors method Trait for WfActivityResult Domain Service 
*
* The accessor methods are provided so that we can build programs without 
* bothering about the implementation details of the required modules. 
* The compiler will fully infer all the required modules to complete the task. 
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfActivityResultServiceApiAccessors {

  def getByWfActivityId(wfActivityId: TableDirect): RIO[WfActivityResultService with WfActivityResultRepository, List[WfActivityResult]] =
   RIO.accessM(_.get.getByWfActivityId(wfActivityId))
   
  def getByAttributeName(attributeName: String): RIO[WfActivityResultService with WfActivityResultRepository, List[WfActivityResult]] =
   RIO.accessM(_.get.getByAttributeName(attributeName))
   
  def getByWfActivityResultId(wfActivityResultId: Id): RIO[WfActivityResultService with WfActivityResultRepository, Option[WfActivityResult]] =
   RIO.accessM(_.get.getByWfActivityResultId(wfActivityResultId))
   
  def getByDescription(description: Option[String]): RIO[WfActivityResultService with WfActivityResultRepository, List[WfActivityResult]] =
   RIO.accessM(_.get.getByDescription(description))
   

  def getAll: RIO[WfActivityResultService with WfActivityResultRepository, List[WfActivityResult]] =
    RIO.accessM(_.get.getAll)

  def getAllByClientId(tenantId: TableDirect): RIO[WfActivityResultService with WfActivityResultRepository, List[WfActivityResult]] =
    RIO.accessM(_.get.getAllByClientId(tenantId))
    
    def save(wfActivityResult: WfActivityResult): RIO[WfActivityResultService with WfActivityResultRepository, WfActivityResult] =
    RIO.accessM(_.get.save(wfActivityResult))  
   
}