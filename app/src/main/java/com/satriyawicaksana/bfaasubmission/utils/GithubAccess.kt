package com.satriyawicaksana.bfaasubmission.utils

class GithubAccess {
    companion object {
        const val API_KEY = "token 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"
        const val ACCEPT = "application/vnd.github.v3+json"
        fun urlUserList(q: String) : String{
            return "https://api.github.com/search/users?q=${q.trim()}"
        }
        fun urlDetailUser(username: String): String{
            return "https://api.github.com/users/${username.trim()}"
        }
        fun urlFollowerUser(username: String):String{
            return "https://api.github.com/users/${username.trim()}/followers"
        }
        fun urlFollowingUser(username: String):String{
            return "https://api.github.com/users/${username.trim()}/following"
        }

    }
}
