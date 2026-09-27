module Test.Main where

import Prelude

import Effect (Effect)
import Effect.Console (log)
import Node.Process (getEnv, cwd, pid, version, execPath, platform, setEnv, unsetEnv, lookupEnv, argv, argv0, uptime, ppid, setExitCode, getExitCode, stdinIsTTY)
import Test.Assert (assert, assertEqual)
import Data.Maybe (isJust, Maybe(..))
import Foreign.Object as Object
import Data.Array (length)

main :: Effect Unit
main = do
  env <- getEnv
  -- Basic sanity checks
  assert $ Object.size env > 0
  
  c <- cwd
  assert $ c /= ""
  
  -- Evaluate pid
  let _ = pid
  
  let _ = version
  
  exec <- execPath
  assert $ exec /= ""
  
  assert $ isJust platform
  
  -- Test argv
  args <- argv
  assert $ (length args) >= 1
  a0 <- argv0
  assert $ a0 /= ""

  -- Test new OS bindings
  up <- uptime
  assert $ up >= 0.0

  -- Test ppid
  let _ = ppid
  
  -- Test exit code
  setExitCode 0
  ec <- getExitCode
  assertEqual { expected: Just 0, actual: ec }

  -- Test TTY
  let _ = stdinIsTTY

  -- Test env manipulation
  setEnv "GOPURS_TEST_ENV" "hello"
  val <- lookupEnv "GOPURS_TEST_ENV"
  assertEqual { expected: Just "hello", actual: val }
  
  unsetEnv "GOPURS_TEST_ENV"
  val2 <- lookupEnv "GOPURS_TEST_ENV"
  assertEqual { expected: Nothing, actual: val2 }
  
  log "Tests passed 🎉"
