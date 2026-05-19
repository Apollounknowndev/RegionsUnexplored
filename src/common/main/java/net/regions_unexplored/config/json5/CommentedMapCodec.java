package net.regions_unexplored.config.json5;

import com.mojang.serialization.*;

import java.util.Optional;
import java.util.stream.Stream;

public class CommentedMapCodec<A> extends MapCodec<A> {
	private final MapCodec<A> delegate;
	private final String key;
	private final String comment;
	
	private CommentedMapCodec(MapCodec<A> delegate, String key, String comment) {
		this.delegate = delegate;
		this.key = key;
		this.comment = comment;
	}
	
	public static <A> CommentedMapCodec<A> commented(Codec<A> delegate, String key, String comment) {
		return commented(delegate.fieldOf(key), key, comment);
	}
	
	public static <A> CommentedMapCodec<Optional<A>> optionalCommented(Codec<A> delegate, String key, String comment) {
		return commented(delegate.optionalFieldOf(key), key, comment);
	}
	
	public static <A> CommentedMapCodec<Optional<A>> lenientOptionalCommented(Codec<A> delegate, String key, String comment) {
		return commented(delegate.optionalFieldOf(key), key, comment);
	}
	
	public static <A> CommentedMapCodec<A> commented(MapCodec<A> delegate, String key, String comment) {
		return new CommentedMapCodec<>(delegate, key, comment);
	}
	
	@Override
	public <T> Stream<T> keys(DynamicOps<T> ops) {
		return this.delegate.keys(ops);
	}
	
	@Override
	public <T> DataResult<A> decode(DynamicOps<T> ops, MapLike<T> input) {
		return this.delegate.decode(ops, input);
	}
	
	@Override
	public <T> RecordBuilder<T> encode(A input, DynamicOps<T> ops, RecordBuilder<T> prefix) {
		RecordBuilder<T> encoded = this.delegate.encode(input, ops, prefix);
		if (encoded instanceof Json5Ops.Json5RecordBuilder json5builder) {
			json5builder.putComment(this.key, this.comment);
		}
		return encoded;
	}
}
