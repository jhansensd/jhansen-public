using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms.core.misc {
    class LinkedHashEntry<Key, Value> : IEnumerable<LinkedHashEntry<Key, Value>> {
        private Key key;
        private Value value;
        LinkedHashEntry<Key, Value> next;

        internal LinkedHashEntry(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public LinkedHashEntry<Key, Value> Next { get; set; }

        public Key GetKey() { return key; }
        public Value GetValue() { return value; }

        public void SetKey(Key key) { this.key = key; }
        public void SetValue(Value value) { this.value = value; }

        public void append(Key key, Value value) {
            LinkedHashEntry<Key, Value> entry = this;
            while (entry.next != null) {
                entry = entry.next;
            }
            entry.next = new LinkedHashEntry<Key, Value>(key, value);
        }

        public LinkedHashEntry<Key, Value> remove(Key key) {
            LinkedHashEntry<Key, Value> entry = this;

            if (entry.key.Equals(key)) {
                return entry;
            }

            while (entry.next != null) {
                if (entry.next.GetKey().Equals(key)) {
                    LinkedHashEntry<Key, Value> next = entry.next;
                    entry.next = entry.next.next;
                    return next;
                }
                entry = entry.next;
            }

            return null;
        }

        public IEnumerator<LinkedHashEntry<Key, Value>> GetEnumerator() {
            LinkedHashEntry<Key, Value> current = this;
            while (current != null) {
                yield return current;
                current = current.next;
            }
        }

        System.Collections.IEnumerator System.Collections.IEnumerable.GetEnumerator() {
            return GetEnumerator();
        }
    }
}