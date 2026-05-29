package net.regions_unexplored.config;

import com.google.gson.JsonParseException;
import com.mojang.serialization.DataResult;
import de.marhali.json5.Json5Element;
import de.marhali.json5.stream.Json5Lexer;
import de.marhali.json5.stream.Json5Parser;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.config.json5.Json5Ops;
import net.regions_unexplored.config.state.client.RUClientConfig;
import net.regions_unexplored.config.state.common.RUCommonConfig;
import net.regions_unexplored.platform.ConfigHelper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class RUConfigHandler {
	public static RUClientConfig CLIENT = RUClientConfig.DEFAULT;
	private static final Path CLIENT_PATH = ConfigHelper.getConfigDirectory().resolve("client.json");
	
	public static RUCommonConfig COMMON = RUCommonConfig.DEFAULT;
	private static final Path COMMON_PATH = ConfigHelper.getConfigDirectory().resolve("common.json");
	
	private static final List<String> LEGACY_CONFIG_NAMES = List.of(
		"client.toml",
		"common.toml",
		"regions_unexplored-common.toml",
		"regions_unexplored-primary-region.toml",
		"regions_unexplored-secondary-region.toml"
	);
	
	public static void loadConfigs() {
		try {
			Files.createDirectories(ConfigHelper.getConfigDirectory());
		} catch (IOException e) {
			throw new RuntimeException("Couldn't create RU config folder, uh oh: " + e);
		}
		
		RUConfigHandler.loadClient();
		RUConfigHandler.loadCommon();
		
		for (String legacyConfigName : LEGACY_CONFIG_NAMES) {
			try {
				Files.deleteIfExists(ConfigHelper.getConfigDirectory().resolve(legacyConfigName));
			} catch (IOException ignored) {
			
			}
		}
	}
	
	public static void loadClient() {
		if (!Files.isRegularFile(CLIENT_PATH)) {
			saveClient();
		}
		
		try (BufferedReader reader = Files.newBufferedReader(CLIENT_PATH)) {
			Json5Element json = Json5Parser.parse(new Json5Lexer(reader, Json5Ops.OPTIONS));
			DataResult<RUClientConfig> parsed = RUClientConfig.CODEC.parse(Json5Ops.INSTANCE, json);
			Optional<RUClientConfig> result = parsed.result();
			if (result.isPresent()) {
				CLIENT = result.get();
				saveClient();
			} else {
				throw new JsonParseException("Invalid codec");
			}
		} catch (JsonParseException e) {
			RegionsUnexplored.LOGGER.error("Couldn't parse client config file, loading default config");
		} catch (Exception e) {
			RegionsUnexplored.LOGGER.error(e.getLocalizedMessage());
			throw new RuntimeException(e);
		}
	}
	
	public static void loadCommon() {
		if (!Files.isRegularFile(COMMON_PATH)) {
			saveCommon();
		}
		
		try (BufferedReader reader = Files.newBufferedReader(COMMON_PATH)) {
			Json5Element json = Json5Parser.parse(new Json5Lexer(reader, Json5Ops.OPTIONS));
			DataResult<RUCommonConfig> parsed = RUCommonConfig.CODEC.parse(Json5Ops.INSTANCE, json);
			Optional<RUCommonConfig> result = parsed.result();
			if (result.isPresent()) {
				COMMON = result.get();
				saveCommon();
			} else {
				throw new JsonParseException("Invalid codec");
			}
		} catch (JsonParseException e) {
			RegionsUnexplored.LOGGER.error("Couldn't parse common config file, loading default config");
		} catch (Exception e) {
			RegionsUnexplored.LOGGER.error(e.getLocalizedMessage());
			throw new RuntimeException(e);
		}
	}
	
	public static void saveClient() {
		try (BufferedWriter writer = Files.newBufferedWriter(CLIENT_PATH)) {
			Optional<Json5Element> element = RUClientConfig.CODEC.encodeStart(Json5Ops.INSTANCE, CLIENT).result();
			if (element.isEmpty()) {
				RegionsUnexplored.LOGGER.error("Couldn't save client config file, skipping");
				return;
			}
			writer.write(element.get().toString(Json5Ops.OPTIONS));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void saveCommon() {
		try (BufferedWriter writer = Files.newBufferedWriter(COMMON_PATH)) {
			Optional<Json5Element> element = RUCommonConfig.CODEC.encodeStart(Json5Ops.INSTANCE, COMMON).result();
			if (element.isEmpty()) {
				RegionsUnexplored.LOGGER.error("Couldn't save common config file, skipping");
				return;
			}
			writer.write(element.get().toString(Json5Ops.OPTIONS));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
