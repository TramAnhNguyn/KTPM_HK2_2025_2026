const amqp = require("amqplib");

async function receiveEmails() {
  const connection = await amqp.connect("amqp://localhost");
  const channel = await connection.createChannel();

  const queue = "email_queue";

  await channel.assertQueue(queue, { durable: false });

  console.log("📥 Waiting for emails...");

  channel.consume(queue, (msg) => {
    if (msg !== null) {
      console.log("✅ Received email:", msg.content.toString());
      channel.ack(msg);
    }
  });
}

receiveEmails();
