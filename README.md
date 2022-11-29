# Android Architecture  - Use Cases in Domain layer
### Summary
This sample is written in Kotlin and uses
the following Architecture Components:
 - ViewModel
 - LiveData
 - Data Binding
 - Navigation
 - Room

It introduces a new layer called `domain` where the Use Cases (also called Interactors) live. The 
`domain` layer is where the business logic happens, which is the code that determines what
the app _does_ with the data coming from the repository before it's exposed to the UI for
display.


