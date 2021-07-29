package com.example.androidgraphql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.datastore.generated.model.Todo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.configure(applicationContext)
            Log.i("MyAmplifyApp", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
        }
//    Old API Code
//        val todo: Todo = Todo.builder()
//                .name("hello world")
//                .id("bb7cdedf-0557-4959-bfd7-1de0cd1a6c66")
//               .description("new description")
//                .build()
//
//        Amplify.API.mutate(ModelMutation.update(todo),
//                {
//                    Log.i("MyAmplifyApp", "Added Todo : ${it.data}")
//                    Log.i("MyAmplifyApp", "Added Todo with id: ${it.data.id}")
//                    Log.i("MyAmplifyApp", "Added Todo with name: ${it.data.name}")
//                    Log.i("MyAmplifyApp", "Added Todo with description : ${it.data.description}")
//                },
//
//                { Log.e("MyAmplifyApp", "Create failed", it) }
//        )

//DataStore Code
//Uncomment to Save object
        val post = Todo.builder()
                .name("My First Post jfbjfhuifhhrfhu")
                .description("hello")
                .build()

        Amplify.DataStore.save(post,
                { Log.i("MyAmplifyApp", "Saved a post") },
                { Log.e("MyAmplifyApp", "Save failed", it) }
        )


        Amplify.DataStore.query(Todo::class.java,
                { matches ->
                    if (matches.hasNext()) {
                        Log.i("MyAmplifyApp", "Successful query, found posts.")
                    } else {
                        Log.i("MyAmplifyApp", "Successful query, but no posts.")
                    }
                },
                { Log.e("MyAmplifyApp",  "Error retrieving posts", it) }
        )

//        Amplify.DataStore.query(Todo::class.java, Where.id("8aa49994-093b-4632-a547-9371c367212e"),
//                { matches ->
//                    if (matches.hasNext()) {
//                        val original = matches.next()
//                        val edited = original.copyOfBuilder()
//                                .name("some new todo")
//                               .description("some new desc")
//                                .build()
//                        Amplify.DataStore.save(edited,
//                                { Log.i("MyAmplifyApp", "Updated a post: ${edited.name}") },
//                                { Log.e("MyAmplifyApp", "Update failed", it) }
//                        )
//                    }
//                },
//                { Log.e("MyAmplifyApp", "Query failed", it) }
//        )

        setContentView(R.layout.activity_main)
    }
}