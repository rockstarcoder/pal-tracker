package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private long id = 0L;
    private Map<Long, TimeEntry> repository = new HashMap<Long, TimeEntry>();

    public TimeEntry create(TimeEntry timeEntry) {
        id++;
        timeEntry.setId(id);
        repository.put(id, timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long timeEntryId) {
        return repository.get(timeEntryId);
    }

    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(repository.values());
    }

    public TimeEntry update(long timeEntryId, TimeEntry newTimeEntry) {
        TimeEntry existing = repository.get(timeEntryId);
        if (existing != null) {
            existing.setDate(newTimeEntry.getDate());
            existing.setHours(newTimeEntry.getHours());
            existing.setProjectId(newTimeEntry.getProjectId());
            existing.setUserId(newTimeEntry.getUserId());
        }
        return existing;
    }

    public TimeEntry delete(long timeEntryId) {
        TimeEntry toRemove = repository.get(timeEntryId);
        repository.remove(timeEntryId);
        return toRemove;
    }
}
