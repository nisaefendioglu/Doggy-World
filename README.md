# Doggy World 🐕

DoggyWorld is a native Android application that utilizes a public REST API to display a list of dog breeds and their images. The app makes use of the "Dog API" provided by https://dog.ceo/dog-api/

## Demo
![Screenshot_6](https://github.com/nisaefendioglu/Doggy-World/assets/48391281/a86ff972-d7ec-434c-a417-bc9b4ce4d71b)

## Features

- Displays a list of dog breeds on the main screen, using data fetched from the API.
- Allows users to click on a breed to view a grid of images associated with that breed.
- Implements a single-activity, multiple-fragment architecture.
- Consists of four main sections: Splash Screen, Main Screen, Image Display Screen, and Detail Screen.

### Splash Screen

- The initial screen displayed when the app is launched, indicating that the app is loading.
- Automatically transitions to the Main Screen after 5 seconds.

### Main Screen

- Displays a list of dog breeds.
- Uses either RecyclerView or ListView to present the list.
- Transitions to the Image Display Screen when a breed is selected.

### Image Display Screen

- Displays a grid of images associated with the selected breed.
- GridView is used.

### Detail Screen

- Displays the selected image in full-screen mode.

## Getting Started

To run the DoggyWorld app on your local machine, follow these steps:

1. Clone the repository: git clone https://github.com/nisaefendioglu/Doggy-World.git

2. Open the project in Android Studio.

3. Build and run the app on an emulator or a physical device running Android 6 or higher.
