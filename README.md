# Javabot

A **Discord bot** built using the **JDA (Java Discord API)**. This bot is designed following the latest Discord policies and leverages the most recent JDA version for optimal functionality.

## Features

- **Token Management**: Utilizes environment variables (`env`) for secure token storage.
- **Event Reactions**: Listens to user activities and responds via `ListenerAdapter`.
- **Custom Command Interface**: Defines user commands through interfaces for better modularity.
- **MSA Design**: Built with `DefaultShardManagerBuilder` for a microservices architecture.
- **Discord Gateway**: Configured to comply with Discord's latest permission gateway regulations.

## Slash Commands

The following commands are currently implemented:
- **HelloWorld Repeat**: Responds with a repeated "Hello, World!" message.
- **Addition of Two Integers**: Adds two user-provided integers.
- **Embed Helper**: Assists with building Discord message embeds.
- **Bulk Message Deletion**: Deletes a batch of messages from a channel.
- **Voice Channel Management**: Ejects users from a voice channel after a set time (under development).
- **Lotto Number Picker**: Randomly selects lotto numbers.

## Setup

### Requirements

- **Java 11+**
- **JDA (Java Discord API)**

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/LEE-Kyungjae/Etude-DiscordBot-JAVA.git
   cd Etude-DiscordBot-JAVA
