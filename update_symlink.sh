#!/bin/sh

# Updates the 'current' symlink to the latest uploaded jar.
CURRENT_VERSION=$(cat version.txt)
ARTIFACT_NAME="html4email-"
ARTIFACT_EXTENSION=".jar"
LINK_NAME="current"

ln -sf "$ARTIFACT_NAME$CURRENT_VERSION$ARTIFACT_EXTENSION" "$LINK_NAME"