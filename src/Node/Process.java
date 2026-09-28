    // Port of Node/Process.js. Environment variables are emulated with an
    // overlay map because the JVM cannot mutate its own environment.
    private static final java.util.Map<String, String> __envOverlay = new java.util.concurrent.ConcurrentHashMap<>();
    private static final java.util.Set<String> __envRemoved = java.util.concurrent.ConcurrentHashMap.newKeySet();
    private static final long __startNanos = System.nanoTime();
    private static final String __platform =
        System.getProperty("os.name", "").toLowerCase().contains("mac") ? "darwin"
            : System.getProperty("os.name", "").toLowerCase().contains("win") ? "win32"
            : "linux";
    private static volatile int __exitCode = 0;
    private static volatile String __title = "";
    private static volatile String __cwd = System.getProperty("user.dir", "");

    private static java.util.Map<String, Object> __environment() {
        java.util.Map<String, Object> env = new java.util.LinkedHashMap<>();
        for (java.util.Map.Entry<String, String> entry : System.getenv().entrySet()) {
            if (!__envRemoved.contains(entry.getKey())) env.put(entry.getKey(), entry.getValue());
        }
        env.putAll(__envOverlay);
        return env;
    }

    public static Object process = new java.util.LinkedHashMap<String, Object>();

    public static Object abortImpl = (java.util.function.Supplier<Object>) () -> { System.exit(134); return null; };

    public static Object argv = (java.util.function.Supplier<Object>) () -> new Object[]{"java", "MainRun"};
    public static Object argv0 = (java.util.function.Supplier<Object>) () -> "java";
    public static Object channelRefImpl = null;
    public static Object channelUnrefImpl = null;

    public static Object chdirImpl = (java.util.function.Function<Object, Object>) (dir) ->
        (java.util.function.Supplier<Object>) () -> { __cwd = (String) dir; return null; };

    public static Object config = (java.util.function.Supplier<Object>) () -> new java.util.LinkedHashMap<String, Object>();
    public static Object connected = (java.util.function.Supplier<Object>) () -> false;
    public static Object cpuUsage = (java.util.function.Supplier<Object>) () -> __cpuUsage();
    public static Object cpuUsageDiffImpl = (java.util.function.Function<Object, Object>) (previous) ->
        (java.util.function.Supplier<Object>) () -> __cpuUsage();
    public static Object cwd = (java.util.function.Supplier<Object>) () -> __cwd;
    public static Object debugPort = 0;
    public static Object disconnectImpl = null;

    public static Object getEnv = (java.util.function.Supplier<Object>) () -> __environment();
    public static Object unsafeGetEnv = (java.util.function.Supplier<Object>) () -> __environment();
    public static Object setEnvImpl = (java.util.function.Function<Object, Object>) (key) ->
        (java.util.function.Function<Object, Object>) (value) ->
        (java.util.function.Supplier<Object>) () -> {
            __envRemoved.remove((String) key);
            __envOverlay.put((String) key, (String) value);
            return null;
        };
    public static Object unsetEnvImpl = (java.util.function.Function<Object, Object>) (key) ->
        (java.util.function.Supplier<Object>) () -> {
            __envOverlay.remove((String) key);
            __envRemoved.add((String) key);
            return null;
        };

    public static Object execArgv = (java.util.function.Supplier<Object>) () -> new Object[0];
    public static Object execPath = (java.util.function.Supplier<Object>) () -> System.getProperty("java.home", "");
    public static Object exit = (java.util.function.Supplier<Object>) () -> { System.exit(__exitCode); return null; };
    public static Object exitImpl = (java.util.function.Function<Object, Object>) (code) ->
        (java.util.function.Supplier<Object>) () -> { System.exit(((Number) code).intValue()); return null; };
    public static Object setExitCodeImpl = (java.util.function.Function<Object, Object>) (code) ->
        (java.util.function.Supplier<Object>) () -> { __exitCode = ((Number) code).intValue(); return null; };
    public static Object getExitCodeImpl = (java.util.function.Supplier<Object>) () -> __exitCode;
    public static Object getGidImpl = (java.util.function.Supplier<Object>) () -> null;
    public static Object getUidImpl = (java.util.function.Supplier<Object>) () -> null;
    public static Object hasUncaughtExceptionCaptureCallback = (java.util.function.Supplier<Object>) () -> false;

    public static Object killImpl = (java.util.function.Function<Object, Object>) (pid) ->
        (java.util.function.Supplier<Object>) () -> __kill(pid, null, null);
    public static Object killStrImpl = (java.util.function.Function<Object, Object>) (pid) ->
        (java.util.function.Function<Object, Object>) (signal) ->
        (java.util.function.Supplier<Object>) () -> __kill(pid, signal, null);
    public static Object killIntImpl = (java.util.function.Function<Object, Object>) (pid) ->
        (java.util.function.Function<Object, Object>) (signal) ->
        (java.util.function.Supplier<Object>) () -> __kill(pid, null, signal);
    private static Object __kill(Object pid, Object signal, Object number) {
        ProcessHandle.of(((Number) pid).longValue()).ifPresent(ProcessHandle::destroy);
        return null;
    }

    public static Object memoryUsage = (java.util.function.Supplier<Object>) () -> {
        Runtime runtime = Runtime.getRuntime();
        java.util.Map<String, Object> usage = new java.util.LinkedHashMap<>();
        usage.put("rss", runtime.totalMemory() - runtime.freeMemory());
        usage.put("heapTotal", runtime.totalMemory());
        usage.put("heapUsed", runtime.totalMemory() - runtime.freeMemory());
        usage.put("external", 0);
        return usage;
    };
    public static Object memoryUsageRss = (java.util.function.Supplier<Object>) () ->
        Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

    public static Object nextTickImpl = (java.util.function.Function<Object, Object>) (callback) ->
        (java.util.function.Supplier<Object>) () -> {
            ((java.util.function.Supplier<Object>) callback).get();
            return null;
        };
    public static Object nextTickCbImpl = (java.util.function.Function<Object, Object>) (callback) ->
        (java.util.function.Function<Object, Object>) (argument) ->
        (java.util.function.Supplier<Object>) () -> {
            Object effect = ((java.util.function.Function<Object, Object>) callback).apply(argument);
            ((java.util.function.Supplier<Object>) effect).get();
            return null;
        };

    public static Object pid = (int) ProcessHandle.current().pid();
    public static Object platformStr = __platform;
    public static Object ppid = ProcessHandle.current().parent().map(ProcessHandle::pid).orElse(0L).intValue();
    public static Object resourceUsage = (java.util.function.Supplier<Object>) () -> new java.util.LinkedHashMap<String, Object>();

    public static Object sendImpl = (java.util.function.Function<Object, Object>) (message) ->
        (java.util.function.Function<Object, Object>) (handle) ->
        (java.util.function.Supplier<Object>) () -> false;
    public static Object sendOptsImpl = (java.util.function.Function<Object, Object>) (message) ->
        (java.util.function.Function<Object, Object>) (handle) ->
        (java.util.function.Function<Object, Object>) (options) ->
        (java.util.function.Supplier<Object>) () -> false;
    public static Object sendCbImpl = (java.util.function.Function<Object, Object>) (message) ->
        (java.util.function.Function<Object, Object>) (handle) ->
        (java.util.function.Function<Object, Object>) (callback) ->
        (java.util.function.Supplier<Object>) () -> false;
    public static Object sendOptsCbImpl = (java.util.function.Function<Object, Object>) (message) ->
        (java.util.function.Function<Object, Object>) (handle) ->
        (java.util.function.Function<Object, Object>) (options) ->
        (java.util.function.Function<Object, Object>) (callback) ->
        (java.util.function.Supplier<Object>) () -> false;

    public static Object setUncaughtExceptionCaptureCallbackImpl = (java.util.function.Function<Object, Object>) (callback) ->
        (java.util.function.Supplier<Object>) () -> null;
    public static Object clearUncaughtExceptionCaptureCallback = (java.util.function.Supplier<Object>) () -> null;

    public static Object stdin = null;
    public static Object stdout = null;
    public static Object stderr = null;
    public static Object stdinIsTTY = false;
    public static Object stdoutIsTTY = System.console() != null;
    public static Object stderrIsTTY = System.console() != null;

    public static Object getTitle = (java.util.function.Supplier<Object>) () -> __title;
    public static Object setTitleImpl = (java.util.function.Function<Object, Object>) (title) ->
        (java.util.function.Supplier<Object>) () -> { __title = (String) title; return null; };
    public static Object uptime = (java.util.function.Supplier<Object>) () -> (System.nanoTime() - __startNanos) / 1e9;
    public static Object version = "v22.0.0";

    private static java.util.Map<String, Object> __cpuUsage() {
        java.util.Map<String, Object> usage = new java.util.LinkedHashMap<>();
        usage.put("user", ProcessHandle.current().info().totalCpuDuration().map(java.time.Duration::toNanos).orElse(0L) / 1e6);
        usage.put("system", 0);
        return usage;
    }
