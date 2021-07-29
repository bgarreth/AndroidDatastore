# AndroidDatastore

In the schema.graphql generate the following model

```
type Todo @model {
  id: ID!
  name: String!
  description: String
}
```
To create the Models Run

```
amplify codegen models
```
