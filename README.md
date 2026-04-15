# Sports News App

## Overview

Sports News App is an Android application developed in **Java** using **Android Studio**. The project was created to practise important Android development concepts such as **Fragments**, **RecyclerView**, **Adapters**, and **SharedPreferences**.

The app simulates a sports news platform where users can browse sports stories, view featured matches, open detailed news pages, and save favourite stories using bookmarks.

---

## Features

- Single Activity Architecture using Fragments
- Home screen with Featured Matches section
- Latest Sports News feed using RecyclerView
- Story detail screen with image and description
- Related Stories section
- Bookmark favourite stories
- Bookmarks screen to view saved items
- Back navigation between screens
- Real sports images for improved UI

---

## Technologies Used

- Java
- Android Studio
- RecyclerView
- Fragments
- SharedPreferences
- XML Layout Design
- ConstraintLayout / LinearLayout

---

## Project Structure

SportsNewsApp/
├── MainActivity.java  
├── HomeFragment.java  
├── DetailFragment.java  
├── BookmarksFragment.java  
├── NewsAdapter.java  
├── FeaturedAdapter.java  
├── NewsItem.java  
└── res/  
  ├── layout/  
  └── drawable/

---

## Screens

### Home Screen

- View Bookmarks button
- Featured Matches horizontal RecyclerView
- Latest Sports News vertical RecyclerView
- Filter spinner

### Detail Screen

- Back button
- Bookmark button
- Large story image
- Story title
- Description
- Related Stories RecyclerView

### Bookmarks Screen

- Displays bookmarked stories saved locally
- Allows users to reopen saved stories

---


