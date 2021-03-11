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

import org.eevolution.context.workflow.domain.model.WfNodeNext
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import zio.RIO

/*
* WfNodeNext Service Api Base Trait for WfNodeNext Domain Service
*
* Is a contract to define the repository api base WfNodeNext Domain Service
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfNodeNextServiceApiBase[-R] {
   def getByWfNodeId(wfNodeId: TableDirect): RIO[R, List[WfNodeNext]]

   def getByWfNextId(wfNextId: Table): RIO[R, List[WfNodeNext]]

   def getBySeqNo(seqNo: Int): RIO[R, List[WfNodeNext]]

   def getByDescription(description: Option[String]): RIO[R, List[WfNodeNext]]

   def getByWfNodeNextId(wfNodeNextId: Id): RIO[R, Option[WfNodeNext]]

    def getAll: RIO[R, List[WfNodeNext]]
    
    def getAllByClientId(tenantId: TableDirect): RIO[R, List[WfNodeNext]]
    
    def save(wfNodeNext: WfNodeNext): RIO[R, WfNodeNext]
}