# My Navigation Demo App For Android

## Running Espresso Tests on Sauce
### Install `saucectl`
```shell
curl -L https://saucelabs.github.io/saucectl/install | bash
```

> ⚠️ Make sure saucectl version is newer than **v0.44.0**

## Running
The configuraton file can be found in the [`.sauce`](/.sauce)-folder. You can run the configurations by running
the following command from the root of this folder

      saucectl run --config ./.sauce/config.yml
