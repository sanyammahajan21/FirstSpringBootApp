package quantsage.Spring_boot_backend.database.repository

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import quantsage.Spring_boot_backend.database.model.User

interface UserRepository: MongoRepository<User, ObjectId> {
    fun findByEmail(email: String): User?
}