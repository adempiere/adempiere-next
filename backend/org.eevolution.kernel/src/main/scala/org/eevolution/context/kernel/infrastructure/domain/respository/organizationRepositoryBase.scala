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
import org.eevolution.context.kernel.domain.organizationRepositoryApi
import org.eevolution.context.kernel.domain.organizationRepositoryApi.OrganizationRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage.{
  Id,
  Organization,
  TableDirect
}
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try

trait organizationRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
      val context: JdbcContext[Dialect, Naming],
      val blocking: Blocking.Service
  ) extends organizationRepositoryApi.OrganizationRepository.Service {

    import context._

    override def getById(
        organizationId: Id
    ): RIO[OrganizationRepository, Option[Organization]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(
              query[Organization]
                .filter(_.organizationId == lift(organizationId))
            ).map(_.headOption)
          )
        )
      )

    override def getAll: RIO[OrganizationRepository, List[Organization]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Organization])
          )
        )
      )

    override def getAllByClientId(
        clientId: TableDirect
    ): RIO[OrganizationRepository, List[Organization]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Organization].filter(_.clientId == lift(clientId)))
          )
        )
      )

    override def save(organization: Organization): RIO[Any, Organization] =
      RIO.fromTry(
        Try(EntityWrapper.save(organization, organizationProperties))
      )

    implicit val organizationSchemaMeta: context.SchemaMeta[Organization] =
      schemaMeta[Organization](
        "AD_Org",
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
        _.replicationStrategyId -> "AD_ReplicationStrategy_ID",
        _.parentOrganizationId -> "Parent_Org_ID",
        _.uuid -> "UUID"
      )

    def organizationProperties: Seq[(String, String)] =
      organizationSchemaMeta.entity.ast.quat.renames.toSeq
  }
}
