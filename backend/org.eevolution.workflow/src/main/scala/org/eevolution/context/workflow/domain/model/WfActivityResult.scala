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
* WfActivityResult Algebraic Data Types domain model
* Workflow Activity Result
* Result of the Workflow Process Activity
* 
*/

case class WfActivityResult(
				clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
				orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
				wfActivityId : TableDirect, //AD_WF_Activity_ID - Workflow Activity - Workflow Activity
				attributeName : String, //AttributeName - Attribute Name - Name of the Attribute
				createdBy : Table, //CreatedBy - Created By - User who created this records
				updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
				updated : DateTime, //Updated - Updated - Date this record was updated
				isActive : YesNo = true, //IsActive - Active - The record is active in the system
				attributeValue : Option[String], //AttributeValue - Attribute Value - Value of the Attribute
				created : DateTime, //Created - Created - Date this record was created
				wfActivityResultId : Id, //AD_WF_ActivityResult_ID - Workflow Activity Result - Result of the Workflow Process Activity
				description : Option[String], //Description - Description - Optional short description of the record
				help : Option[String], //Help - Comment/Help - Comment or Hint
				uuid : Option[String], //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "AD_WF_ActivityResult"
	override val identifier: String = "AD_WF_ActivityResult_ID"
	override val Id: Id = wfActivityResultId
}


object WfActivityResult {
	def create(
			clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
			orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
			wfActivityId : TableDirect, //AD_WF_Activity_ID - Workflow Activity - Workflow Activity
			attributeName : String, //AttributeName - Attribute Name - Name of the Attribute
			createdBy : Table, //CreatedBy - Created By - User who created this records
			updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
			updated : DateTime, //Updated - Updated - Date this record was updated
			isActive : YesNo = true, //IsActive - Active - The record is active in the system
			attributeValue : Option[String], //AttributeValue - Attribute Value - Value of the Attribute
			created : DateTime, //Created - Created - Date this record was created
			wfActivityResultId : Id, //AD_WF_ActivityResult_ID - Workflow Activity Result - Result of the Workflow Process Activity
			description : Option[String], //Description - Description - Optional short description of the record
			help : Option[String], //Help - Comment/Help - Comment or Hint
			uuid : Option[String] //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
	) : WfActivityResult = WfActivityResult(
						clientId, //AD_Client_ID - Client - Client/Tenant for this installation.
						orgId, //AD_Org_ID - Organization - Organizational entity within client
						wfActivityId, //AD_WF_Activity_ID - Workflow Activity - Workflow Activity
						attributeName, //AttributeName - Attribute Name - Name of the Attribute
						createdBy, //CreatedBy - Created By - User who created this records
						updatedBy, //UpdatedBy - Updated By - User who updated this records
						updated, //Updated - Updated - Date this record was updated
						isActive, //IsActive - Active - The record is active in the system
						attributeValue, //AttributeValue - Attribute Value - Value of the Attribute
						created, //Created - Created - Date this record was created
						wfActivityResultId, //AD_WF_ActivityResult_ID - Workflow Activity Result - Result of the Workflow Process Activity
						description, //Description - Description - Optional short description of the record
						help, //Help - Comment/Help - Comment or Hint
						uuid)  //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
}
