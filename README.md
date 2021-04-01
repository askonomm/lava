# Bloggo

A blog-oriented static site generator that supports static content in the form of Markdown files as well as 
dynamic content in the form of Mustache templates, allowing you to create flexible websites.

- [Installation](#installation)
  - [Linux](#linux)
  - [macOS](#macos)
  - [Windows](#windows)
- [Updating](#updating)
- [Usage](#usage)
  - [Directory structure](#directory-structure)
  - [Content files](#content-files)
    - [Markdown](#markdown)
    - [Mustache](#mustache)
  - [Site layout](#site-layout)
  - [Template data](#template-data)
  - [Site configuration](#site-configuration)
  - [Command-line usage](#command-line-usage)
- [Example sites](#example-sites)

## Installation

### Linux

1. Download the `bloggo-linux` executable from the [latest release](https://github.com/soynomm/bloggo/releases)
2. Rename it to something more friendly like `bloggo` with `mv bloggo-linux bloggo`
3. Make it executable via `chmod +x bloggo`
4. Run it with `./bloggo` (or move it to `/usr/local/bin` to access globally as `bloggo`)

### macOS

1. Download the `bloggo-mac` executable from the [latest release](https://github.com/soynomm/bloggo/releases)
2. Rename it to something more friendly like `bloggo` with `mv bloggo-mac bloggo`
3. Make it executable via `chmod +x bloggo`
4. Run it with `./bloggo` (or move it to `/usr/local/bin` to access globally as `bloggo`)

### Windows

1. Download the `bloggo.exe` executable from the [latest release](https://github.com/soynomm/bloggo/releases)
3. Run it with `.\bloggo.exe` (or add it to PATH to access globally as `bloggo`)

### Java

To run it on any platform that has Java, you'll need Java 11+, download the `bloggo.jar` file and run it with `java -jar bloggo.jar`.

## Updating

To update any previous version you might have, simply overwrite/replace your existing Bloggo executable with a new one.

## Usage

Using Bloggo is straight-forward in that you just need to have a resources directory that contains everything your static
website needs, like a `config.json` file for configuration, a `layout.mustache` file for the website template as well as a `content` 
directory inside the resources directory for all of the content files that make up your website.

### Directory structure

By default Bloggo is looking for a resources directory called `resources` (you can specify any other directory by calling `bloggo -r {directory}` or `bloggo --resources {directory}`).
The resources directory structure needs to look like this:

- resources/
  - content/
    - hello-world.md
    - author/
      - john.md
  - config.json
  - layout.mustache

### Content files

All of the content files reside inside the `content` directory. There are two types of content files - Markdown (file.md) and Mustache (file.mustache).
Markdown content files are meant for static content, such as blog posts and pages. Mustache 
content files are meant for custom, dynamic pages.

#### Markdown

A Markdown content file looks like this:

```markdown
---
title: Hello, World
date: 2020-12-01
---

Hi there, world.
```

Meta-data of each Markdown content file goes in-between 3 hyphens and the entry of the content file itself will go below the meta-data.

#### Mustache

A Mustache content file can have any structure you want. You can check example content files of [my own website](https://github.com/soynomm/nomm.xyz) to see
what things I've made with Mustache content files for examples.

### Site layout

To be written.

### Template data

To be written.

### Site configuration

To be written.

### Command-line usage

To be written.

## Example sites

- [Nomm](https://nomm.xyz) ([Github repository](https://github.com/soynomm/nomm.xyz))
