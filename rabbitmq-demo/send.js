const amqp = require("amqplib");

async function sendEmails() {
  const connection = await amqp.connect("amqp://localhost");
  const channel = await connection.createChannel();

  const queue = "email_queue";

  await channel.assertQueue(queue, { durable: false });

  const emails = [
    "user1@gmail.com",
    "user2@gmail.com",
    "user3@gmail.com",
    "user4@gmail.com",
    "user5@gmail.com"
  ];

  for (let email of emails) {
    channel.sendToQueue(queue, Buffer.from(email));
    console.log("📤 Sent email:", email);
  }

  setTimeout(() => {
    connection.close();
    process.exit(0);
  }, 500);
}

sendEmails();
