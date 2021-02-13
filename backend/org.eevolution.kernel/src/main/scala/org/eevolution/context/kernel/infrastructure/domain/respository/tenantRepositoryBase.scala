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

package org.eevolution.context.kernel.infrastructure.domain.respository

import io.getquill.NamingStrategy
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom
import org.eevolution.context.kernel.domain.tenantRepositoryApi
import org.eevolution.context.kernel.domain.tenantRepositoryApi.TenantRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage.{
  Id,
  TableDirect,
  Tenant
}
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try

trait tenantRepositoryBase {
  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
      val context: JdbcContext[Dialect, Naming],
      val blocking: Blocking.Service
  ) extends tenantRepositoryApi.TenantRepository.Service {

    import context._

    override def getById(
        tenantId: Id
    ): RIO[TenantRepository, Option[Tenant]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(
              query[Tenant]
                .filter(_.tenantId == lift(tenantId))
            ).map(_.headOption)
          )
        )
      )

    override def getAll: RIO[TenantRepository, List[Tenant]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Tenant])
          )
        )
      )

    override def getAllByClientId(
        tenantId: TableDirect
    ): RIO[TenantRepository, List[Tenant]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Tenant].filter(_.tenantId == lift(tenantId)))
          )
        )
      )

    override def save(tenant: Tenant): RIO[Any, Tenant] =
      RIO.fromTry(
        Try(EntityWrapper.save(tenant, tenantProperties))
      )

    implicit val tenantSchemaMeta: context.SchemaMeta[Tenant] =
      schemaMeta[Tenant](
        "AD_Client",
        _.tenantId -> "C_Currency_ID",
        _.tenantId -> "AD_Client_ID",
        _.organizationId -> "AD_Org_ID",
        _.isActive -> "IsActive",
        _.created -> "Created",
        _.createdBy -> "CreatedBy",
        _.updated -> "Updated",
        _.updatedBy -> "UpdatedBy",
        _.value -> "Value",
        _.name -> "Name",
        _.description -> "Description",
        _.requestEmail -> "RequestEMail",
        _.requestUser -> "RequestUser",
        _.requestUserPW -> "RequestUserPW",
        _.requestFolder -> "RequestFolder",
        _.language -> "AD_Language",
        _.isMultilingualDocument -> "IsMultiLingualDocument",
        _.isUseBetaFunctions -> "IsUseBetaFunctions",
        _.ldapQuery -> "LDAPQuery",
        _.modelValidationClasses -> "ModelValidationClasses",
        _.autoArchive -> "AutoArchive",
        _.mmPolicy -> "MMPolicy",
        _.emailTest -> "EMailTest",
        _.isServerEmail -> "IsServerEmail",
        _.documentDir -> "DocumentDir",
        _.isPostImmediate -> "IsPostImmediate",
        _.isCostImmediate -> "IsCostImmediate",
        _.isStoreAttachmentsOnFileSystem -> "StoreAttachmentsOnFileSystem",
        _.windowsAttachmentPath -> "WindowsAttachmentPath",
        _.unixAttachmentPath -> "UnixAttachmentPath",
        _.isStoreArchiveOnFileSystem -> "StoreArchiveOnFileSystem",
        _.windowsArchivePath -> "WindowsArchivePath",
        _.unixArchivePath -> "UnixArchivePath",
        _.isUseASP -> "IsUseASP",
        _.replicationStrategyId -> "AD_ReplicationStrategy_ID",
        _.emailConfigId -> "AD_EMailConfig_ID",
        _.uuid -> "UUID"
      )

    def tenantProperties: Seq[(String, String)] =
      tenantSchemaMeta.entity.ast.quat.renames.toSeq
  }
}
