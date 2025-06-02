# Base image
FROM redis:7.2-alpine

# Copy custom sentinel config (optional)
COPY sentinel.conf /etc/redis/sentinel.conf

# Entry point can be overwritten in docker-compose
CMD ["redis-sentinel", "/etc/redis/sentinel.conf"]