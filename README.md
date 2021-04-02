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

Using Bloggo is straight-forward in that you just need to have a resources' directory that contains everything your static
website needs, like a `config.json` file for configuration, a `layout.mustache` file for the website template as well as a `content` 
directory inside the resources' directory for all the content files that make up your website.

### Directory structure

By default, Bloggo is looking for a resources' directory called `resources` (you can specify any other directory by calling `bloggo -r {directory}` or `bloggo --resources {directory}`).
The resources' directory structure needs to look like this:

- resources/
  - content/
    - hello-world.md
    - author/
      - john.md
  - config.json
  - layout.mustache
  
### Site layout

The site layout of your static website lives inside a Mustache template file `layout.mustache` in the root of the resources' directory.
That layout file has all the [template data](#template-data) available to it just like all Mustache content files do, and just
like all Mustache content files, it also can have any structure you want it to have. 

You can check out [my own website' layout.mustache](https://github.com/soynomm/nomm.xyz/layout.mustache) for an example use-case.

### Content files

All the content files reside inside the `content` directory. There are two types of content files - Markdown ({filename}.md) and Mustache ({filename}.mustache).
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

Meta-data of each Markdown content file goes in-between 3 hyphens, and the entry of the content file itself will go below the meta-data.
Markdown content file data is available to you via `{{metakey}}` Mustache templating, for example the above content file would be
available via the following Mustache variables:

- `{{title}}` - Renders the meta-data title value
- `{{date}}` - Renders the default date in the format of `EEE, dd MMM yyyy HH:mm:ss Z`
- `{{date_unparsed}}` - Renders the date as it is in the content file
- `{{pretty_date}}` - Renders the date in the format of `MMM dd, yyyy`
- `{{pretty_date_without_year}}` - Renders the date in the format of `MMM dd`
- `{{{entry}}}` - Renders the entry-block Markdown into HTML. Yes, needs 3 brackets.

#### Mustache

A Mustache content file can have any structure you want. You can check out [my own website content files](https://github.com/soynomm/nomm.xyz) for example use-cases. 
It's important to note that unlike a Markdown content file, a Mustache content file does not use site layout 
and thus enables (and encourages) an entirely new layout for each Mustache content file.

### Template data

In all of your Mustache files (including content files and site layout), the following data is available for use:

#### `is_home`

Returns true if the user visits the home page of the site.

Example usage:

```mustache
{{#is_home}}
<p>Hi! Welcome to my website.</p>
{{/is_home}}
```

#### `is_post`

Returns true if the user visits any of the content files.

Example usage:

```mustache
{{#is_post}}
  <h2>{{title}}></h2>
  <div class="date">{{pretty_date}}</div>
  <div class="entry">{{{entry}}}</div>
{{/is_post}}
```

### Site configuration

To be written.

### Command-line usage

To be written.

## Example sites

- [Nomm](https://nomm.xyz) ([Github repository](https://github.com/soynomm/nomm.xyz))
