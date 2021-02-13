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

package org.eevolution.context.kernel.domain

import org.eevolution.context.kernel.domain.userRepositoryApi.UserRepository
import org.eevolution.context.kernel.domain.userServiceApi.UserService
import org.eevolution.context.kernel.domain.service.userServiceBase
import zio.ZLayer

package object userServiceModule extends userServiceBase {

  private case class Live(userRepository: UserRepository.Service)
      extends Service(userRepository)

  object UserServiceLayer {
    val live: ZLayer[UserRepository, Nothing, UserService] =
      ZLayer.fromService((userRepository) => Live(userRepository))

  }

}
