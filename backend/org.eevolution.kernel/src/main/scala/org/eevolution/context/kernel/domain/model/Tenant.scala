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
 **/

package org.eevolution.context.kernel.domain.model

import java.time.LocalDateTime

import org.eevolution.context.kernel.domain.ubiquitouslanguage.{DateTime, _}

/**
  * Tenant Entity
  *
  * @param tenantId                       Tenant ID
  * @param organizationId                 Organization ID
  * @param isActive                       Is Active
  * @param created                        Created
  * @param createdBy                      Created By
  * @param updated                        Updated
  * @param updatedBy                      Updated By
  * @param value                          Value
  * @param name                           Name
  * @param description                    Description
  * @param requestEmail                   Request Email
  * @param requestUser                    Request User
  * @param requestUserPW                  Request User Pw
  * @param requestFolder                  Request Folder
  * @param language                       Language
  * @param isMultilingualDocument         Is Multilingual Document
  * @param isUseBetaFunctions             Is Use Beta Functions
  * @param ldapQuery                      LDAP Query
  * @param modelValidationClasses         modelValidationClasses
  * @param autoArchive                    Is Auto Archive
  * @param mmPolicy                       MM Policy
  * @param emailTest                      Email Test
  * @param isServerEmail                  Is Server Email
  * @param documentDir                    Document Dir
  * @param isPostImmediate                Is Post Immediate
  * @param isCostImmediate                Is Cost Immediate
  * @param isStoreAttachmentsOnFileSystem Is Store Attachments On File System
  * @param windowsAttachmentPath          Window Attachment Path
  * @param unixAttachmentPath             Unix Attachment Path
  * @param isStoreArchiveOnFileSystem     Is Store Archive On File System
  * @param windowsArchivePath             Windows Archive Path
  * @param unixArchivePath                Unix Archive Path
  * @param isUseASP                       Is Use ASP
  * @param replicationStrategyId          Replication Strategy ID
  * @param emailConfigId                  Email Config ID
  */
case class Tenant(
    tenantId: Id,
    organizationId: TableDirect = 0,
    isActive: YesNo = true,
    created: DateTime = LocalDateTime.now,
    createdBy: Table,
    updated: DateTime = LocalDateTime.now,
    updatedBy: Table,
    value: String,
    name: String,
    description: String,
    requestEmail: String,
    requestUser: String,
    requestUserPW: String,
    requestFolder: String,
    language: String,
    isMultilingualDocument: YesNo = false,
    isUseBetaFunctions: YesNo = false,
    ldapQuery: String,
    modelValidationClasses: String,
    autoArchive: String,
    mmPolicy: String = "F",
    emailTest: YesNo,
    isServerEmail: YesNo = false,
    documentDir: String,
    isPostImmediate: YesNo = false,
    isCostImmediate: YesNo = false,
    isStoreAttachmentsOnFileSystem: YesNo = false,
    windowsAttachmentPath: String,
    unixAttachmentPath: String,
    isStoreArchiveOnFileSystem: YesNo = false,
    windowsArchivePath: String,
    unixArchivePath: String,
    isUseASP: YesNo = false,
    replicationStrategyId: TableDirect,
    emailConfigId: TableDirect,
    uuid: String
) extends DomainModel
    with ActiveEnabled
    with Identifiable
    with Traceable {
  override type ActiveEnabled = this.type
  override type Identifiable = this.type
  override type Traceable = this.type

  override def Id: Int = tenantId

  override val entityName: String = "AD_Client"
  override val identifier: String = "AD_Client_ID"
}

object Tenant {
  //implicit lazy val jsonFormat = Jsonx.formatCaseClass[Tenant]
  def create(
      tenantId: Id,
      organizationId: TableDirect,
      isActive: YesNo,
      created: DateTime,
      createdBy: Table,
      updated: DateTime,
      updatedBy: Table,
      value: String,
      name: String,
      description: String,
      requestEmail: String,
      requestUser: String,
      requestUserPW: String,
      requestFolder: String,
      language: String,
      isMultilingualDocument: YesNo,
      isUseBetaFunctions: YesNo,
      ldapQuery: String,
      modelValidationClasses: String,
      autoArchive: String,
      mmPolicy: String,
      emailTest: YesNo,
      isServerEmail: YesNo,
      documentDir: String,
      isPostImmediate: YesNo,
      isCostImmediate: YesNo,
      isStoreAttachmentsOnFileSystem: YesNo,
      windowsAttachmentPath: String,
      unixAttachmentPath: String,
      storeArchiveOnFileSystem: YesNo,
      windowsArchivePath: String,
      unixArchivePath: String,
      isUseASP: YesNo,
      replicationStrategyId: TableDirect,
      emailConfigId: TableDirect,
      uuid: String
  ) =
    Tenant(
      tenantId,
      organizationId,
      isActive,
      created,
      createdBy,
      updated,
      updatedBy,
      value,
      name,
      description,
      requestEmail,
      requestUser,
      requestUserPW,
      requestFolder,
      language,
      isMultilingualDocument,
      isUseBetaFunctions,
      ldapQuery,
      modelValidationClasses,
      autoArchive,
      mmPolicy,
      emailTest,
      isServerEmail,
      documentDir,
      isPostImmediate,
      isCostImmediate,
      isStoreAttachmentsOnFileSystem,
      windowsAttachmentPath,
      unixAttachmentPath,
      storeArchiveOnFileSystem,
      windowsArchivePath,
      unixArchivePath,
      isUseASP,
      replicationStrategyId,
      emailConfigId,
      uuid
    )
}
