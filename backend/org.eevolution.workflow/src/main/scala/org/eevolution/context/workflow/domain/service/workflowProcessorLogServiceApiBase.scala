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

import org.eevolution.context.workflow.domain.model.WorkflowProcessorLog
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import zio.RIO

/*
* WorkflowProcessorLog Service Api Base Trait for WorkflowProcessorLog Domain Service
*
* Is a contract to define the repository api base WorkflowProcessorLog Domain Service
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait workflowProcessorLogServiceApiBase[-R] {
   def getByWorkflowProcessorId(workflowProcessorId: TableDirect): RIO[R, List[WorkflowProcessorLog]]

   def getByWorkflowProcessorLogId(workflowProcessorLogId: Id): RIO[R, Option[WorkflowProcessorLog]]

   def getByDescription(description: Option[String]): RIO[R, List[WorkflowProcessorLog]]

    def getAll: RIO[R, List[WorkflowProcessorLog]]
    
    def getAllByClientId(tenantId: TableDirect): RIO[R, List[WorkflowProcessorLog]]
    
    def save(workflowProcessorLog: WorkflowProcessorLog): RIO[R, WorkflowProcessorLog]
}