{
  description = "KomoUI — Kotlin Multiplatform UI component library";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = nixpkgs.legacyPackages.${system};
        jdk = pkgs.zulu17;
      in
      {
        devShells.default = pkgs.mkShell {
          packages = [
            jdk
            pkgs.git
          ];

          shellHook = ''
            export JAVA_HOME="${jdk.home}"

            # Auto-detect Android SDK installed by Android Studio (macOS default).
            if [ -z "$ANDROID_HOME" ] && [ -d "$HOME/Library/Android/sdk" ]; then
              export ANDROID_HOME="$HOME/Library/Android/sdk"
            fi
            if [ -n "$ANDROID_HOME" ] && [ -z "$ANDROID_SDK_ROOT" ]; then
              export ANDROID_SDK_ROOT="$ANDROID_HOME"
            fi

            echo "KomoUI dev shell"
            echo "  JAVA_HOME=$JAVA_HOME"
            echo "  ANDROID_HOME=''${ANDROID_HOME:-(unset — Android builds will fail)}"
            java -version 2>&1 | head -1
          '';
        };
      });
}
