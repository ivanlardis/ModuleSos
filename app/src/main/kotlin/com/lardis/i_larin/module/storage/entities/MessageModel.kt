package com.lardis.i_larin.module.storage.entities

data class MessageModel constructor(val id: Long?,
                                    val message: String,
                                    val idAuthor: Int,
                                    val idDialog: Long,
                                    val timeCreate: Long)
