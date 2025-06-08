package quantsage.Spring_boot_backend.controllers

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.web.bind.annotation.*
import quantsage.Spring_boot_backend.database.model.Note
import quantsage.Spring_boot_backend.database.repository.NoteRepository
import java.time.Instant

@RestController
@RequestMapping("/notes")
class NoteController(
    private val repository: NoteRepository
) {

    data class NoteRequest(
        val id: String?,
        val title: String,
        val content: String,
        val color: Long,
    )

    data class NoteResponse(
        val id: String?,
        val title: String,
        val content: String,
        val color: Long,
        val createdAt: Instant,
    )

    @PostMapping
    fun save(
        @RequestBody body : NoteRequest
    ): NoteResponse {
        val note = repository.save(
            Note(
                id = body.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = body.title,
                content = body.content,
                color = body.color,
                createdAt = Instant.now(),
                ownerId = ObjectId(),

            )
        )

        return note.toResponse()
    }
    @GetMapping
    fun findByOwnerId(
        @RequestParam(required = true) ownerId: String
    ): List<NoteResponse> {
        return repository.findByOwnerId(ObjectId(ownerId)).map{
            it.toResponse()
        }
    }
    @DeleteMapping(path = ["/{id}"])
    fun deleteById(@PathVariable("id") id: String) {
        repository.deleteById(ObjectId(id))
    }
}

private fun Note.toResponse(): NoteController.NoteResponse {
    return NoteController.NoteResponse(
        id = id.toHexString(),
        title = title,
        content = content,
        color = color,
        createdAt = createdAt,
    )
}