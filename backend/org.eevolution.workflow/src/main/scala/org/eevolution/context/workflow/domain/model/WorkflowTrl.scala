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

package org.eevolution.context.workflow.domain.model

import org.eevolution.context.kernel.domain.model._
import org.eevolution.context.kernel.domain.ubiquitouslanguage._

/**
* WorkflowTrl Algebraic Data Types domain model
* Workflow Trl
* Workflow or combination of tasks
* 
*/

case class WorkflowTrl(
				workflowId : TableDirect, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
				language : Table, //AD_Language - Language - Language for this entity
				name : String, //Name - Name - Alphanumeric identifier of the entity
				description : Option[String], //Description - Description - Optional short description of the record
				help : Option[String], //Help - Comment/Help - Comment or Hint
				isTranslated : YesNo, //IsTranslated - Translated - This column is translated
				isActive : YesNo = true, //IsActive - Active - The record is active in the system
				created : DateTime, //Created - Created - Date this record was created
				createdBy : Table, //CreatedBy - Created By - User who created this records
				updated : DateTime, //Updated - Updated - Date this record was updated
				updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
				clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
				orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
				uuid : Option[String], //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
) extends DomainModel
with ActiveEnabled
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	val entityName: String = "AD_Workflow_Trl"
}


object WorkflowTrl {
	def create(
			workflowId : TableDirect, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
			language : Table, //AD_Language - Language - Language for this entity
			name : String, //Name - Name - Alphanumeric identifier of the entity
			description : Option[String], //Description - Description - Optional short description of the record
			help : Option[String], //Help - Comment/Help - Comment or Hint
			isTranslated : YesNo, //IsTranslated - Translated - This column is translated
			isActive : YesNo = true, //IsActive - Active - The record is active in the system
			created : DateTime, //Created - Created - Date this record was created
			createdBy : Table, //CreatedBy - Created By - User who created this records
			updated : DateTime, //Updated - Updated - Date this record was updated
			updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
			clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
			orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
			uuid : Option[String] //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
	) : WorkflowTrl = WorkflowTrl(
						workflowId, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
						language, //AD_Language - Language - Language for this entity
						name, //Name - Name - Alphanumeric identifier of the entity
						description, //Description - Description - Optional short description of the record
						help, //Help - Comment/Help - Comment or Hint
						isTranslated, //IsTranslated - Translated - This column is translated
						isActive, //IsActive - Active - The record is active in the system
						created, //Created - Created - Date this record was created
						createdBy, //CreatedBy - Created By - User who created this records
						updated, //Updated - Updated - Date this record was updated
						updatedBy, //UpdatedBy - Updated By - User who updated this records
						clientId, //AD_Client_ID - Client - Client/Tenant for this installation.
						orgId, //AD_Org_ID - Organization - Organizational entity within client
						uuid)  //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
}
