using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms.core.misc {
    class ListNode<Item> : IEnumerable<Item> {
        private ListNode<Item> next;
        private Item data;

        public ListNode(Item data, ListNode<Item> next) {
            this.data = data;
            this.next = next;
        }

        public ListNode<Item> Next { get; set; }
        public Item Data { get; set; }

        public IEnumerator<Item> GetEnumerator() {
            ListNode<Item> current = this;
            while (current != null) {
                yield return current.data;
                current = current.next;
            }
        }

        System.Collections.IEnumerator System.Collections.IEnumerable.GetEnumerator() {
            return GetEnumerator();
        }

    }
}
